package com.cn.cloud.core.support.databind;

import java.text.DateFormat;
/**
 * 
 * @ClassName   : JsonDateFormatConverter.java
 * @Description : 时间格式接口
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
public interface JsonDateFormatConverter {
    public DateFormat dateFormat();
}
