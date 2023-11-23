package com.github.phoswald.sample.jaxb;

import static javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.jupiter.api.Test;

class DocumentTest {
    
    private static final JAXBContext context = createContext();
    
    @Test
    void roundTrip_xmlDateTime_full() {
        String input = "<document><xmlDateTime>2002-05-30T09:30:10.456+06:00</xmlDateTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlDateTime();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonth());
        assertEquals(30, value.getDay());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(456, value.getMillisecond());
        assertEquals(6 * 60, value.getTimezone());
    }

    @Test
    void roundTrip_xmlDateTime_localNoFraction() {
        String input = "<document><xmlDateTime>2002-05-30T09:30:10</xmlDateTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlDateTime();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonth());
        assertEquals(30, value.getDay());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(FIELD_UNDEFINED, value.getMillisecond());
        assertEquals(FIELD_UNDEFINED, value.getTimezone());
    }
    
    @Test
    void roundTrip_xmlDate_full() {
        String input = "<document><xmlDate>2002-05-30+06:00</xmlDate></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlDate();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonth());
        assertEquals(30, value.getDay());
        assertEquals(FIELD_UNDEFINED, value.getHour());
        assertEquals(FIELD_UNDEFINED, value.getMinute());
        assertEquals(FIELD_UNDEFINED, value.getSecond());
        assertEquals(FIELD_UNDEFINED, value.getMillisecond());
        assertEquals(6 * 60, value.getTimezone());
    }
    
    @Test
    void roundTrip_xmlDate_local() {
        String input = "<document><xmlDate>2002-05-30</xmlDate></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlDate();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonth());
        assertEquals(30, value.getDay());
        assertEquals(FIELD_UNDEFINED, value.getHour());
        assertEquals(FIELD_UNDEFINED, value.getMinute());
        assertEquals(FIELD_UNDEFINED, value.getSecond());
        assertEquals(FIELD_UNDEFINED, value.getMillisecond());
        assertEquals(FIELD_UNDEFINED, value.getTimezone());
    }
    
    @Test
    void roundTrip_xmlTime_full() {
        String input = "<document><xmlTime>09:30:10.456+06:00</xmlTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlTime();
        
        assertEquals(input, output);
        assertEquals(FIELD_UNDEFINED, value.getYear());
        assertEquals(FIELD_UNDEFINED, value.getMonth());
        assertEquals(FIELD_UNDEFINED, value.getDay());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(456, value.getMillisecond());
        assertEquals(6 * 60, value.getTimezone());
    }

    @Test
    void roundTrip_xmlTime_noFraction() {
        String input = "<document><xmlTime>09:30:10</xmlTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        XMLGregorianCalendar value = object.getXmlTime();
        
        assertEquals(input, output);
        assertEquals(FIELD_UNDEFINED, value.getYear());
        assertEquals(FIELD_UNDEFINED, value.getMonth());
        assertEquals(FIELD_UNDEFINED, value.getDay());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(FIELD_UNDEFINED, value.getMillisecond());
        assertEquals(FIELD_UNDEFINED, value.getTimezone());
    }
    
    @Test
    void roundTrip_javaDateTime_full() {
        String input = "<document><javaDateTime>2002-05-30T09:30:10.456+06:00</javaDateTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        ZonedDateTime value = object.getJavaDateTime();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonthValue());
        assertEquals(30, value.getDayOfMonth());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(456 * 1_000_000, value.getNano());
        assertEquals(ZoneOffset.ofHours(6), value.getOffset());
    }
    
    @Test
    void roundTrip_javaDateTime_noFraction() {
        String input = "<document><javaDateTime>2002-05-30T09:30:10+06:00</javaDateTime></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        ZonedDateTime value = object.getJavaDateTime();
        
        assertEquals(input, output.replace(".000", "")); // TODO: fraction is added
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonthValue());
        assertEquals(30, value.getDayOfMonth());
        assertEquals(9, value.getHour());
        assertEquals(30, value.getMinute());
        assertEquals(10, value.getSecond());
        assertEquals(0, value.getNano());
        assertEquals(ZoneOffset.ofHours(6), value.getOffset());
    }
    
    @Test
    void roundTrip_javaDate_full() {
        String input = "<document><javaDate>2002-05-30+06:00</javaDate></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        LocalDate value = object.getJavaDate();
        
        assertEquals(input.replace("+06:00", ""), output); // TODO: zone is lost
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonthValue());
        assertEquals(30, value.getDayOfMonth());
    }
    
    @Test
    void roundTrip_javaDate_local() {
        String input = "<document><javaDate>2002-05-30</javaDate></document>";
        Document object = unmarshal(input);
        String output = marshal(object);
        LocalDate value = object.getJavaDate();
        
        assertEquals(input, output);
        assertEquals(2002, value.getYear());
        assertEquals(5, value.getMonthValue());
        assertEquals(30, value.getDayOfMonth());
    }
    
    private Document unmarshal(String xml) {
        try {
            return (Document) context.createUnmarshaller().unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    private String marshal(Document obj) {
        try(StringWriter writer = new StringWriter()) {
            context.createMarshaller().marshal(obj, writer);
            return writer.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        } catch (JAXBException | IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private static JAXBContext createContext() {
        try {
            return JAXBContext.newInstance(Document.class);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}
