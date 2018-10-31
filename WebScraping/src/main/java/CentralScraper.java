import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CentralScraper {
	ErrorLog errorLog = new ErrorLog();
	DataTable dataTable = new DataTable();
	
	public static void main(String[] args) throws IOException {
		CentralScraper CS = new CentralScraper();
		for (String arg : args) {
			PMCAbstract PA = new PMCAbstract(CS.getErrorLog());
			String[] row = PA.scrapePMCAbstract(arg);
			CS.dataTable.addRow(row);
			CS.errorLog = PA.getErrorLog();
		}
		
		// Store Data in CSV File
		String path = "C:\\Users\\gavst\\OneDrive - cumc.columbia.edu\\Computer Science";
		CS.CSVdataWriter(path);
		CS.CSVerrorWriter(path);
		
		// Store Error Log in 
	}
	
	public ErrorLog getErrorLog() {
		return errorLog;
	}
	
	// Make CSV File out of Scraped Data
	public void CSVerrorWriter(String filePath) throws IOException {
		// first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath);  
        
	    // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 
  
        // create CSVWriter object filewriter object as parameter 
        CSVWriter writer = new CSVWriter(outputfile); 
  
  
        // loop through DataTable 
        List<String> DT = errorLog.getLog();
        String[] DTarray = DT.toArray(new String[0]);
        writer.writeNext(DTarray); 
        
        // closing writer connection 
        writer.close(); 
	}
	
	// Make CSV File out of Error Log
	public void CSVdataWriter(String filePath) throws IOException 
	{ 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    File file = new File(filePath);  
        
	    // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter(file); 
  
        // create CSVWriter object filewriter object as parameter 
        CSVWriter writer = new CSVWriter(outputfile); 
  
        // adding header to csv 
        String[] columnNames = { "Title", "Authors", "Journal", 
        		"Publication_Date", "PubMed_URL", "Journal_URL", "DOI", "PMID", "PMCID", 
        		"Keywords", "MeSH_Keywords", "References", "Source" };
        writer.writeNext(columnNames); 
  
        // loop through DataTable 
        List<String[]> DT = dataTable.getDataList();
        for(int i = 1; i <= DT.size(); i++) {
        	writer.writeNext(DT.get(i)); 
        }
        
        // closing writer connection 
        writer.close(); 
	} 
}
