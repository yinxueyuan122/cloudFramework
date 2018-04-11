package com.cn.cloud.common.file.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class FileUtil {
	
	  /**
     * 路径分隔符
     */
    public static final String SEPARATOR = "/";
    /**
     * Point
     */
    public static final String POINT = ".";
    
    /**
     * 图片类型
     */
    private static final List<String> TYPE_IMAGE = new ArrayList<>();
    /**
     * 文档类型
     */
    private static final List<String> TYPE_DOC = new ArrayList<>();
    /**
     * 音频类型
     */
    private static final List<String> TYPE_VIDEO = new ArrayList<>();
    /**
     * 压缩文件类型
     */
    private static final List<String> TYPE_COMPRESS = new ArrayList<>();
    
    /**
     * ContentType
     */
    public static final Map<String, String> EXT_MAPS = new HashMap<>();

    static {
        TYPE_IMAGE.add("png");
        TYPE_IMAGE.add("gif");
        TYPE_IMAGE.add("jpeg");
        TYPE_IMAGE.add("jpg");

        TYPE_DOC.add("pdf");
        TYPE_DOC.add("ppt");
        TYPE_DOC.add("xls");
        TYPE_DOC.add("xlsx");
        TYPE_DOC.add("pptx");
        TYPE_DOC.add("doc");
        TYPE_DOC.add("docx");

        TYPE_VIDEO.add("mp3");
        TYPE_VIDEO.add("mp4");
        TYPE_VIDEO.add("flv");

        TYPE_COMPRESS.add("zip");
        TYPE_COMPRESS.add("rar");
        
        // image
        EXT_MAPS.put("png", "image/png");
        EXT_MAPS.put("gif", "image/gif");
        EXT_MAPS.put("bmp", "image/bmp");
        EXT_MAPS.put("ico", "image/x-ico");
        EXT_MAPS.put("jpeg", "image/jpeg");
        EXT_MAPS.put("jpg", "image/jpeg");
        // 压缩文件
        EXT_MAPS.put("zip", "application/zip");
        EXT_MAPS.put("rar", "application/x-rar");
        // doc
        EXT_MAPS.put("pdf", "application/pdf");
        EXT_MAPS.put("ppt", "application/vnd.ms-powerpoint");
        EXT_MAPS.put("xls", "application/vnd.ms-excel");
        EXT_MAPS.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        EXT_MAPS.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        EXT_MAPS.put("doc", "application/msword");
        EXT_MAPS.put("doc", "application/wps-office.doc");
        EXT_MAPS.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        EXT_MAPS.put("txt", "text/plain");
        // 音频
        EXT_MAPS.put("mp4", "video/mp4");
        EXT_MAPS.put("flv", "video/x-flv");
    }

    /**
     * 检查图片类型. <br>
     * 默认检查 ['png', 'gif', 'jpeg', 'jpg'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkImage(String filename){
        return checkImage(null, filename);
    }

    /**
     * 检查图片类型
     * @param types 可自行传入文件的类型集合，默认检查 ['png', 'gif', 'jpeg', 'jpg'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkImage(List<String> types, String filename){
        List<String> checkTypes = types;
        if(types == null || types.size() == 0){
            checkTypes = TYPE_IMAGE;
        }

        return checkType(checkTypes, filename);
    }

    /**
     * 检查文档类型. <br>
     * 默认检查 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkDoc(String filename){
        return checkDoc(null, filename);
    }

    /**
     * 检查文档类型
     * @param types 可自行传入文件的类型集合，默认检查 ['pdf', 'ppt', 'xls', 'xlsx', 'pptx', 'doc', 'docx'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkDoc(List<String> types, String filename){
        List<String> checkTypes = types;
        if(types == null || types.size() == 0){
            checkTypes = TYPE_DOC;
        }

        return checkType(checkTypes, filename);
    }

    /**
     * 检查音频类型. <br>
     * 默认检查 ['mp3', 'mp4', 'flv'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkVideo(String filename){
        return checkVideo(null, filename);
    }

    /**
     * 检查音频类型
     * @param types 可自行传入文件的类型集合，默认检查 ['mp3', 'mp4', 'flv'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkVideo(List<String> types, String filename){
        List<String> checkTypes = types;
        if(types == null || types.size() == 0){
            checkTypes = TYPE_VIDEO;
        }

        return checkType(checkTypes, filename);
    }

    /**
     * 检查压缩文件类型. <br>
     * 默认检查 ['zip', 'rar'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkCompress(String filename){
        return checkCompress(null, filename);
    }

    /**
     * 检查压缩文件类型
     * @param types 可自行传入文件的类型集合，默认检查 ['zip', 'rar'] 几种类型
     * @param filename 文件名称
     * @return
     */
    public static boolean checkCompress(List<String> types, String filename){
        List<String> checkTypes = types;
        if(types == null || types.size() == 0){
            checkTypes = TYPE_COMPRESS;
        }

        return checkType(checkTypes, filename);
    }

    /**
     * 检查类型通用方法
     */
    private static boolean checkType(List<String> checkTypes, String filename){
        return checkTypes.contains(getFilenameSuffix(filename));
    }

    /**
     * 获取文件名称的后缀
     *
     * @param filename
     * @return 文件后缀
     */
    public static String getFilenameSuffix(String filename) {
        String suffix = null;
        if (StringUtils.isNotBlank(filename) && filename.contains(POINT)) {
            suffix = filename.substring(filename.lastIndexOf(POINT) + 1).toLowerCase();
        }
        return suffix;
    }
    
    /**
     * 获取文件类型
     * @param filename
     * @return
     */
    public static String getFileContentType(String filename){
    	return EXT_MAPS.get(getFilenameSuffix(filename));
    }
    
    
}
