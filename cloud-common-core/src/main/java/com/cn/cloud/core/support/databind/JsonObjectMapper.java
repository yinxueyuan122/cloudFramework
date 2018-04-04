package com.cn.cloud.core.support.databind;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
/**
 * 
 * @ClassName   : JsonObjectMapper.java
 * @Description : ObjectMapper 实现类 注入JsonDateFormatConverter
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
public class JsonObjectMapper extends ObjectMapper {

    /**
     * Statements
     * (long)serialVersionUID
     */
    private static final long serialVersionUID = -7944352723419678521L;

    private String dateFormat = "yyyy-MM-dd";
    private JsonDateFormatConverter jsonDateFormatConverter = null;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        if(jsonDateFormatConverter != null){
            setDateFormat(jsonDateFormatConverter.dateFormat());
        }else{
            setDateFormat(new SimpleDateFormat(dateFormat));
        }

        registerModule(new SimpleCustomModule());
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void setJsonDateFormatConverter(
            JsonDateFormatConverter jsonDateFormatConverter) {
        this.jsonDateFormatConverter = jsonDateFormatConverter;
    }

    class SimpleCustomModule extends SimpleModule {

        /**
         * Statements
         * (long)serialVersionUID
         */
        private static final long serialVersionUID = 6680121643562687181L;

        public SimpleCustomModule() {
            addDeserializer(String.class, new StdScalarDeserializer<String>(String.class){

                /**
                 * Statements
                 * (long)serialVersionUID
                 */
                private static final long serialVersionUID = -8204271819956731194L;

                @Override
                public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException,
                        JsonProcessingException {
                    return StringUtils.trim(jp.getValueAsString());
                }

            });
        }
    }
}
