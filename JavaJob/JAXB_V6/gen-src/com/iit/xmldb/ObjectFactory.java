//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.02.22 at 12:06:16 AM IST 
//


package com.iit.xmldb;

import javax.xml.bind.annotation.XmlRegistry;
import com.iit.xmldb.EmpInfo;
import com.iit.xmldb.EmpType;
import com.iit.xmldb.ObjectFactory;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.iit.xmldb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.iit.xmldb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmpType }
     * 
     */
    public EmpType createEmpType() {
        return new EmpType();
    }

    /**
     * Create an instance of {@link EmpInfo }
     * 
     */
    public EmpInfo createEmpInfo() {
        return new EmpInfo();
    }

}
