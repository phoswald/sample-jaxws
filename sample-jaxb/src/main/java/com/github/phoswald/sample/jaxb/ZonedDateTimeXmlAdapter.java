package com.github.phoswald.sample.jaxb;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ZonedDateTimeXmlAdapter extends XmlAdapter<String, ZonedDateTime> {

   @Override
   public ZonedDateTime unmarshal(String v) throws Exception {
      if (v == null) {
         return null;
      } else {
         return ZonedDateTime.parse(v, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      }
   }

   @Override
   public String marshal(ZonedDateTime v) throws Exception {
      if (v == null) {
         return null;
      } else {
         return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(v);
      }
   }
}
