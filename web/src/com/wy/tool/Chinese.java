package com.wy.tool;

public class Chinese {
    public  static String  toChinese(String strvalue) {
           try {
               if (strvalue == null) {
                   return "";
               } else {
                   strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
                   return strvalue;
               }
           } catch (Exception e) {
               return "";
           }
    }
}
