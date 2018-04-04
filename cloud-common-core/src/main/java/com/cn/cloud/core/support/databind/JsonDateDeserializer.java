package com.cn.cloud.core.support.databind;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
/**
 * 
 * @ClassName   : JsonDateDeserializer.java
 * @Description : 日期改成 中国日期
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
public class JsonDateDeserializer extends JsonDeserializer<Date>  {

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {

	    if(StringUtils.isBlank(jsonParser.getText())){
	        return null;
	    }

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

            TimeZone serverTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
            int serverTimeZoneOffset = serverTimeZone.getRawOffset()/60000;
            int clientTimeZoneOffset = serverTimeZoneOffset;

            ServletRequestAttributes servletRequestAttribute = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();

            if(servletRequestAttribute.getRequest().getHeader("Client-TimeZone-Offset") != null){
                clientTimeZoneOffset = Integer.parseInt(servletRequestAttribute.getRequest().getHeader("Client-TimeZone-Offset"));
            }

            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(jsonParser.getText()));

            if(serverTimeZoneOffset != clientTimeZoneOffset){
                calendar.add(Calendar.MINUTE, clientTimeZoneOffset - serverTimeZoneOffset);
            }

            return calendar.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
	}

}
