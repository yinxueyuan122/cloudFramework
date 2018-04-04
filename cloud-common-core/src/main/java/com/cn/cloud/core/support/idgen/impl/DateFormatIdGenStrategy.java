package com.cn.cloud.core.support.idgen.impl;

import org.joda.time.LocalDate;

public class DateFormatIdGenStrategy extends MixedIdGenStrategy {
	
   private String datePattern = "yyyyMMdd";
   private String delimeter = "";
   
   public String getDatePattern() {
     return datePattern;
   }
   
   public void setDatePattern(String datePattern) {
     this.datePattern = datePattern;
   }
   
   public String getDelimeter() {
     return delimeter;
   }
   
   public void setDelimeter(String delimeter) {
     this.delimeter = delimeter;
   }
   
   public String getPrefix()
   {
     return super.getPrefix() + LocalDate.now().toString(datePattern) + delimeter;
   }
}
