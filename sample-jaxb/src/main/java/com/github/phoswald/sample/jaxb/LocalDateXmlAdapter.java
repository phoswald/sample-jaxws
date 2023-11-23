package com.github.phoswald.sample.jaxb;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

   @Override
   public LocalDate unmarshal(String v) throws Exception {
      if (v == null) {
         return null;
      } else {
         return LocalDate.parse(v, DateTimeFormatter.ISO_DATE);
      }
   }

   @Override
   public String marshal(LocalDate v) throws Exception {
      if(v == null) {
         return null;
      } else {
         return DateTimeFormatter.ISO_DATE.format(v); 
      }
   }
}
