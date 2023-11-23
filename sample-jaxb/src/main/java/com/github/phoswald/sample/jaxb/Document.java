package com.github.phoswald.sample.jaxb;

import java.time.ZonedDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlRootElement(name = "document")
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {
    
    @XmlElement
    protected String string;
    
    @XmlElement   
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar xmlDateTime;
    
    @XmlElement   
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar xmlDate;
    
    @XmlElement   
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar xmlTime;
    
    @XmlElement
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class) // TODO can we make this optional?
    protected ZonedDateTime javaDateTime;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public XMLGregorianCalendar getXmlDateTime() {
        return xmlDateTime;
    }

    public void setXmlDateTime(XMLGregorianCalendar xmlDateTime) {
        this.xmlDateTime = xmlDateTime;
    }

    public XMLGregorianCalendar getXmlDate() {
        return xmlDate;
    }

    public void setXmlDate(XMLGregorianCalendar xmlDate) {
        this.xmlDate = xmlDate;
    }

    public XMLGregorianCalendar getXmlTime() {
        return xmlTime;
    }

    public void setXmlTime(XMLGregorianCalendar xmlTime) {
        this.xmlTime = xmlTime;
    }

    public ZonedDateTime getJavaDateTime() {
        return javaDateTime;
    }

    public void setJavaDateTime(ZonedDateTime javaDateTime) {
        this.javaDateTime = javaDateTime;
    }
}
