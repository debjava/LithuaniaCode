//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.01.16 at 12:03:33 AM IST 
//


package com.iit.xmldb;

import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iit.xmldb.EmpInfoType;
import com.iit.xmldb.TimeSheetInfo;


/**
 * <p>Java class for TimeSheetInfo element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="TimeSheetInfo">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="EmpInfo" type="{http://com/iit/xmldb/TimeSheetInfo}EmpInfoType"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(AccessType.FIELD)
@XmlType(name = "", propOrder = {
    "empInfo"
})
@XmlRootElement(name = "TimeSheetInfo")
public class TimeSheetInfo {

    @XmlElement(name = "EmpInfo", namespace = "http://com/iit/xmldb/TimeSheetInfo")
    protected EmpInfoType empInfo;

    /**
     * Gets the value of the empInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EmpInfoType }
     *     
     */
    public EmpInfoType getEmpInfo() {
        return empInfo;
    }

    /**
     * Sets the value of the empInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpInfoType }
     *     
     */
    public void setEmpInfo(EmpInfoType value) {
        this.empInfo = value;
    }

}