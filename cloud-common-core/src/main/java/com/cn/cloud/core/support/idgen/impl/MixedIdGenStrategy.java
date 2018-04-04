package com.cn.cloud.core.support.idgen.impl;

import org.apache.commons.lang3.StringUtils;

import com.cn.cloud.core.support.idgen.IdGenStrategy;

public class MixedIdGenStrategy implements IdGenStrategy {
	
   protected String prefix = "";
   protected int cipers = 5;
   protected char paddingChar = '0';
   protected String suffix = "";
   
   public String makeId(String originalId)
   {
     return getPrefix() + StringUtils.leftPad(originalId, cipers, paddingChar) + getSuffix();
   }
   
   public String getPrefix() {
     return prefix;
   }
   
   public void setPrefix(String prefix) {
     this.prefix = prefix;
   }
   
   public int getCipers() {
     return cipers;
   }
   
   public void setCipers(int cipers) {
     this.cipers = cipers;
   }
   
   public char getPaddingChar() {
     return paddingChar;
   }
   
   public void setPaddingChar(char paddingChar) {
     this.paddingChar = paddingChar;
   }
   
   public String getSuffix() {
     return suffix;
   }
   
   public void setSuffix(String suffix) {
     this.suffix = suffix;
   }
}
