import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CentralScraper {
	ErrorLog errorLog = new ErrorLog();
	DataTable dataTable = new DataTable();
	
	public static void main(String[] args) throws IOException {
		
		// Pass in scraped URLs
		String[] URLs = new String[5];
		URLs[0] = "https://www.ncbi.nlm.nih.gov/pubmed/30366941";
		URLs[1] = "https://www.ncbi.nlm.nih.gov/pubmed/30286377";
		URLs[2] = "https://www.ncbi.nlm.nih.gov/pubmed/30366941";
		URLs[3] = "https://www.ncbi.nlm.nih.gov/pubmed/30290272";
		URLs[4] = "https://www.ncbi.nlm.nih.gov/pubmed/30348987";

		
		
		// SCRAPE!!!!!!
		CentralScraper CS = new CentralScraper();
		CS.scrape(URLs);
	}
	
	public void scrape(String[] URLs) throws IOException {
		for (String url : URLs) {
			PMCAbstract PA = new PMCAbstract(getErrorLog());
			String[] row = PA.scrapePMCAbstract(url);
			dataTable.addRow(row);
			errorLog = PA.getErrorLog();
		}
		
		
		String path = "C:\\Users\\gavst\\OneDrive - cumc.columbia.edu\\Computer Science";
		
		// Store Data in CSV File
		CSVdataWriter(path);
		
		// Store Error Log in
		CSVerrorWriter(path);
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
        List<String[]> EL = errorLog.getLog();
        for(int i = 1; i <= EL.size(); i++) {
        	writer.writeNext(EL.get(i)); 
        }
        
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
