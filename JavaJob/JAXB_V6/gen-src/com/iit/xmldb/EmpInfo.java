//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.02.22 at 12:06:16 AM IST 
//


package com.iit.xmldb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.iit.xmldb.EmpInfo;
import com.iit.xmldb.EmpType;


/**
 * <p>Java class for EmpInfo element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="EmpInfo">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="Emp" type="{http://com/iit/xmldb/EmpInfo}EmpType" maxOccurs="unbounded" minOccurs="0"/>
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
    "emp"
})
@XmlRootElement(name = "EmpInfo")
public class EmpInfo {

    @XmlElement(name = "Emp", namespace = "http://com/iit/xmldb/EmpInfo")
    protected List<EmpType> emp;

    /**
     * Gets the value of the emp property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emp property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmp().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmpType }
     * 
     * 
     */
    public List<EmpType> getEmp() {
        if (emp == null) {
            emp = new ArrayList<EmpType>();
        }
        return this.emp;
    }

}
