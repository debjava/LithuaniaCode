package com.dds.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TestOnDate 
{
	public static void main(String[] args) 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Date ::: "+dateFormat.format(date));
        System.out.println("Date in AM/PM ::: "+date.toLocaleString());
        List dateList = new ArrayList();
        //26/01/2007 23:56:03
//        Date dt = "26/01/2007 23:56:03";
        dateList.add( "25/01/2007 20:56:03 PM");
        dateList.add( "25/01/2007 4:56:03 AM");
        dateList.add( "24/01/2007 23:56:03 AM");
        dateList.add( date.toLocaleString());
        System.out.println("Date List :::: "+dateList);
        Collections.sort(dateList);
        System.out.println("Date List :::: "+dateList);
	}

}
