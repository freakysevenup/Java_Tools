package JSON.writer;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class excelUtilities 
{
	jsonwriter jw;
	
	 private String file;
	 
	 Iterator<Row> rowIterator;
	 Iterator<Cell> cellIterator;
	 Row row;
	 Cell cell;
	 
	 boolean isRemote = true;
	 
	 String sessionName;
	 String dC = "desiredCapabilities";
	 String c = "configuration";
	 JSONObject desiredCapabilities;
	 JSONObject configuration;
	 JSONObject session;
	 ObjectMapper mapper = new ObjectMapper();
	 Object json;

	 public void Upload(String file){
	  this.file = file;
	 }

	 public void read() throws IOException, InvalidFormatException  
	 {
         File inputWorkbook = new File(file);
         XSSFWorkbook w = new XSSFWorkbook(inputWorkbook);
         XSSFSheet sheet = null;
       //Get sheet from the workbook
         if(isRemote)
         {
        	 sheet = w.getSheetAt(1);
    		 jsonwriter.setFilePath(true);
         }
         else if(!isRemote)
         {
        	 sheet = w.getSheetAt(0);
    		 jsonwriter.setFilePath(false);
         }
         
       //Get iterator to all the rows in current sheet
         rowIterator = sheet.iterator();

         row = rowIterator.next();
         
		//Get iterator to all cells of current row
         cellIterator = row.cellIterator();
         
         // write the local session json files
         writeFiles();
         
         w.close();
	 }
	 
	 public void writeFiles()
	 { 
        /*
         * In a for loop structure
         * write the json file using the data in the cells of each row
         * save the json file with the name in the first column
         */
		 
		 while(rowIterator.hasNext())
		 {
			 row = rowIterator.next();

			 while(cellIterator.hasNext())
			 {
				cell = cellIterator.next();
				
				sessionName = cell.getStringCellValue();
				// this should be the name in the first cell of the row,
				// it will take this cell's string and use that as the name of the file
				 jw = new jsonwriter(sessionName);
				 
				 session = new JSONObject();
				 
				 // now create an object in the root object to store the data for this session
				 jw.createNewObject(session, sessionName);
				 
				 while(cellIterator.hasNext())
				 {
					 // set up desiredCapabilities
					cell = cellIterator.next();
		
					desiredCapabilities = new JSONObject();
					
					// now create an object in the data object to store the desired capabilities
					jw.createNewObject(desiredCapabilities, dC);
					
					// This should be the second cell in the row, its data is the deviceName
					jw.createVariable(jw.getObject(dC), "deviceName", cell.getStringCellValue());
					 
					 while(cellIterator.hasNext())
					 {
						cell = cellIterator.next();
			
						// This should be the device version cell in this row
						jw.createVariable(jw.getObject(dC), "deviceVersion", cell.getStringCellValue());
						 
						 while(cellIterator.hasNext())
						 {
							cell = cellIterator.next();
				
							// this should be the browser name cell in this row
							jw.createVariable(jw.getObject(dC), "browserName", cell.getStringCellValue());
							 
							 while(cellIterator.hasNext())
							 {
								cell = cellIterator.next();
					
								// this should be the browser version cell in this row
								jw.createVariable(jw.getObject(dC), "browserVersion", cell.getStringCellValue());
								 
								// close and name this element
								jw.createVariable(jw.getObject(sessionName), "desiredCapabilities", jw.getObject(dC));
								
								while(cellIterator.hasNext())
								 {
									 //set up configuration
									 cell = cellIterator.next();
									
									 configuration = new JSONObject();
									 
									 // create a new object to store the data for the configuration
									 jw.createNewObject(configuration, c);
									 
									 // this should be the mobile boolean cell in this row
									 jw.createVariable(jw.getObject(c), "isMobile", cell.getStringCellValue());
									 
									 while(cellIterator.hasNext())
									 {
										 cell = cellIterator.next();
										 
										 // this should be the remote boolean cell in this row
										 jw.createVariable(jw.getObject(c), "isRemote", cell.getStringCellValue());
										 
										 if(isRemote)
										 {
											 while(cellIterator.hasNext())
											 {
												 cell = cellIterator.next();
												 
												 jw.createVariable(jw.getObject(c), "credentials", cell.getStringCellValue());
											 }
										 }
										 
										 // close and name this element
										 jw.createVariable(jw.getObject(sessionName), "configuration", jw.getObject(c));
										 
										 // close and name the session element
										 jw.createVariable(jw.getRoot(), sessionName, jw.getObject(sessionName));
										 
										 try {
											json = mapper.readValue(jw.getRoot().toString(), Object.class);
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										 
										 //write the finalized file
										 try {
											jw.finalize(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
										} catch (JsonProcessingException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									 }
								 }					 
							 } 							 
						 }				
					 }
				 }
			 }
			 cellIterator = row.cellIterator();
		 }
	 }
}
