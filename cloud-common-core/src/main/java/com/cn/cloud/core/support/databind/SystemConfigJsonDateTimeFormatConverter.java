package com.cn.cloud.core.support.databind;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @ClassName   : SystemConfigJsonDateTimeFormatConverter.java
 * @Description : 获取系统设置的时间格式实现类
 * @author Yin Xueyuan
 * @since 2017年3月2日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2017年3月2日        Yin Xueyuan           fisrt create
 * </pre>
 */
public class SystemConfigJsonDateTimeFormatConverter implements JsonDateFormatConverter {

    public static final String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    
    @Override
    public DateFormat dateFormat() {

        DateFormat dateFormat;

        try {
        	//TODO 系统配置时间格式功能完成后 数据库获取系统设置时间格式
        	dateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(defaultDateTimeFormat);
        }

        return dateFormat;
    }


}
