
package com.iit.inhouse.xmlutil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.iit.xmldb.EmpInfoType;
import com.iit.xmldb.ObjectFactory;
import com.iit.xmldb.ProjectTimeSheetType;
import com.iit.xmldb.TimeSheetInfo;

public class JAXBXMLbUtil
{
	private transient FileOutputStream	fout		= null;
	private transient FileInputStream fin = null;

	ObjectFactory						objectFact	= null;

	JAXBContext							jaxbContext	= null;
	

	public JAXBXMLbUtil()
	{
		super();
	}

	public JAXBXMLbUtil(final String filePath)
	{
		super();
		try
		{
			fout = new FileOutputStream( filePath );
			fin = new FileInputStream( filePath );
			if ( fout == null || fin == null )
				throw new NullPointerException(
						"File path not found, provide the actual path ");
			else
			{
				jaxbContext = JAXBContext.newInstance("com.iit.xmldb");
			}
			
		} catch (NullPointerException npe)
		{
			npe.printStackTrace();
		} catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch( JAXBException je )
		{
			je.printStackTrace();
		}
	}

	public boolean createNewXMLFile()
	{
		boolean checkFlag = false;
		try
		{
//			jaxbContext = JAXBContext.newInstance("com.iit.xmldb");
			if( jaxbContext == null )
				throw new NullPointerException( "JAXBContext is null, probably package name may be wrong" );
			else
			{
				objectFact = new ObjectFactory();
				TimeSheetInfo timeSheetInfo = objectFact.createTimeSheetInfo();
				EmpInfoType empInfoType = objectFact.createEmpInfoType();
				
				empInfoType.setEmpName("Balu");
				empInfoType.setGuideName("Raja Mohan");
				empInfoType.setEmpId("149");
				empInfoType.setTotalHours("20");
				List projectTimsheet = empInfoType.getProjectTimeSheet();
				
				ProjectTimeSheetType projTimeSheetType = objectFact.createProjectTimeSheetType();
				
				projTimeSheetType.setActivityHeading("Activity Heading");
				projTimeSheetType.setActivityName("Activity Name");
				projTimeSheetType.setDescription("Valid Description");
				projTimeSheetType.setProjectCode("Project Code");
				projTimeSheetType.setStartDate("Start Date");
				projTimeSheetType.setWorkingHours("working Hours");
//				Adding to project Time Sheet
				projectTimsheet.add(projTimeSheetType);
//				Adding or setting to main node TimeSheetInfo
				timeSheetInfo.setEmpInfo(empInfoType);
				checkFlag = true;
				
				
				//Final writing to XML file
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
				marshaller.marshal( timeSheetInfo , fout );
//				fout.close();
				
			}
		} 
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		} catch (JAXBException e)
		{
			e.printStackTrace();
		}
//		catch (IOException ie) 
//		{
//			ie.printStackTrace();
//		}
		return checkFlag;
	}
	
	public boolean updateXMLFile()
	{
		boolean checkFlag = false;
		try
		{
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			TimeSheetInfo timeSheetInfo = ( TimeSheetInfo ) unmarshaller.unmarshal( fin );
//			System.out.println("JAXBUtil:updateXML() - TimeSheetInfo :::"+timeSheetInfo);
			EmpInfoType empInfoType =  timeSheetInfo.getEmpInfo();
			if( empInfoType != null )
			{
				empInfoType = objectFact.createEmpInfoType();
				
				//For testing
				empInfoType.setEmpName("Balu1");
				empInfoType.setGuideName("Raja Mohan1");
				empInfoType.setEmpId("1491");
				empInfoType.setTotalHours("201");
				
				List projectTimsheet = empInfoType.getProjectTimeSheet();
				
				
				
				ProjectTimeSheetType projTimeSheetType = objectFact.createProjectTimeSheetType();
				
				projTimeSheetType.setActivityHeading("Activity Heading");
				projTimeSheetType.setActivityName("Activity Name");
				projTimeSheetType.setDescription("Valid Description");
				projTimeSheetType.setProjectCode("Project Code");
				projTimeSheetType.setStartDate("Start Date");
				projTimeSheetType.setWorkingHours("working Hours");
//				Adding to project Time Sheet
				
				projectTimsheet.add(projTimeSheetType);
//				Adding or setting to main node TimeSheetInfo
				
				
				
				timeSheetInfo.setEmpInfo(empInfoType);
				checkFlag = true;
				
				//Final writing to XML file
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE );
				marshaller.marshal( timeSheetInfo , fout );
				System.out.println("It is coming here.............");
				fout.close();
				//For testing
			}
			else
			{
				
			}
		}
		catch( NullPointerException npe )
		{
			System.out.println("It is null here");
			npe.printStackTrace();
		}
		catch( JAXBException je )
		{
			je.printStackTrace();
		}
		catch( IOException ie )
		{
			ie.printStackTrace();
		}
		return checkFlag;
	}

	public static void main(String[] args)
	{
		JAXBXMLbUtil jaxbUtil = new JAXBXMLbUtil("E:/JavaJob/JAXBProj/data/newTimeSheet.xml");
		jaxbUtil.createNewXMLFile();
//		boolean testFlag = 
			jaxbUtil.updateXMLFile();//jaxbUtil.createNewXMLFile();
//		System.out.println("testFlag ::: "+testFlag );
	}

}
