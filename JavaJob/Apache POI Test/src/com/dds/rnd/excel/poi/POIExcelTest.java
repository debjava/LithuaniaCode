package com.dds.rnd.excel.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class POIExcelTest 
{
	public static void main(String[] args) 
	{
		try {
            InputStream input = new FileInputStream( "E:/JavaJob/Apache POI Test/Book11.xls" );
            POIFSFileSystem fs = new POIFSFileSystem( input );
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            
            // Iterate over each row in the sheet
            Iterator rows = sheet.rowIterator(); 
            while( rows.hasNext() ) {           
                HSSFRow row = (HSSFRow) rows.next();
                System.out.println( "Row #" + row.getRowNum() );
 
                // Iterate over each cell in the row and print out the cell's content
                Iterator cells = row.cellIterator();
                while( cells.hasNext() ) {
                    HSSFCell cell = (HSSFCell) cells.next();
                    System.out.println( "Cell #" + cell.getCellNum() );
                    switch ( cell.getCellType() ) {
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println( cell.getNumericCellValue() );
                            break;
                        case HSSFCell.CELL_TYPE_STRING: 
                            System.out.println( cell.getStringCellValue() );
                            break;
                        default:
                            System.out.println( "unsuported sell type" );
                            break;
                    }
                }
                
            }
            
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }
	

}
