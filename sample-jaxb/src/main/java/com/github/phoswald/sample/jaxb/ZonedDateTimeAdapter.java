package com.github.phoswald.sample.jaxb;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {

    private static final DatatypeFactory factory = createDatatypeFactory();

    @Override
    public ZonedDateTime unmarshal(String s) throws Exception {
        XMLGregorianCalendar c = factory.newXMLGregorianCalendar(s);
        // TODO: what if c.getMillisecond() == FIELD_UNDEFINED ?
        return ZonedDateTime.of( //
                c.getYear(), c.getMonth(), c.getDay(), //
                c.getHour(), c.getMinute(), c.getSecond(), //
                c.getMillisecond() * 1_000_000, // convert to millis to nanos
                ZoneOffset.ofTotalSeconds(c.getTimezone() * 60)); // convert from minutes
    }

    @Override
    public String marshal(ZonedDateTime v) throws Exception {
        // TODO: there would be a method for fraction instead of millis
        XMLGregorianCalendar c = factory.newXMLGregorianCalendar( //
                v.getYear(), v.getMonthValue(), v.getDayOfMonth(), //
                v.getHour(), v.getMinute(), v.getSecond(), //
                v.getNano() / 1_000_000, // convert from nanos to mills
                v.getOffset().getTotalSeconds() / 60); // convert to minutes
        return c.toXMLFormat();
    }

    private static DatatypeFactory createDatatypeFactory() {
        try {
            return DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException();
        }
    }
}
