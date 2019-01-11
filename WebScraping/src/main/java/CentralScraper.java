import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class CentralScraper {
	ErrorLog errorLog = new ErrorLog();
	DataTable dataTable = new DataTable();
	
	public static void main(String[] args) throws IOException {
		
		// Pass in scraped URLs
		
		
		// Test URLs
		String[] URLs = new String[5];
		URLs[0] = "https://www.ncbi.nlm.nih.gov/pubmed/30366941";
		URLs[1] = "https://www.ncbi.nlm.nih.gov/pubmed/30286377";
		URLs[2] = "https://www.ncbi.nlm.nih.gov/pubmed/30366941";
		URLs[3] = "https://www.ncbi.nlm.nih.gov/pubmed/30290272";
		URLs[4] = "https://www.ncbi.nlm.nih.gov/pubmed/30348987";

		// List of PMIDs to loop through
		Scanner sc = new Scanner(new File("/Users/gavst/documents/pubmed_result_aging_mitochondria.txt"));
		List<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
		  lines.add(sc.nextLine());
		}

		// Add proper link address to each PMID number
		URLs = lines.toArray(new String[0]);
		for(int i =0; i < URLs.length; i++) {
			URLs[i] = "https://www.ncbi.nlm.nih.gov/pubmed/" + URLs[i];
		}
		
		// SCRAPE!!!!!!
		CentralScraper CS = new CentralScraper();
		CS.scrape(URLs);
	}
	
	// Read txt file of URLS
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	public void scrape(String[] URLs) throws IOException {
		for (String url : URLs) {
			PMCAbstract PA = new PMCAbstract(getErrorLog());
			String[] row = PA.scrapePMCAbstract(url);
			dataTable.addRow(row);
			errorLog = PA.getErrorLog();
		}
		
		
		//String path = "/Users/gavst/OneDrive - cumc.columbia.edu/Computer Science";
		
		// Store Data in CSV File
		CSVdataWriter();
		
		// Store Error Log in
		CSVerrorWriter();
	}
	
	public ErrorLog getErrorLog() {
		return errorLog;
	}
	
	// Make CSV File out of Scraped Data
	public void CSVerrorWriter() throws IOException {
		// first create file object for file placed at location 
	    // specified by filepath 
	    //File file = new File(filePath);  
        
	    // create FileWriter object with file as parameter 
        FileWriter outputfile = new FileWriter("/Users/gavst/OneDrive - cumc.columbia.edu/Computer Science/scraper_errors.csv"); 
  
        // create CSVWriter object filewriter object as parameter 
        CSVWriter writer = new CSVWriter(outputfile); 
        
        // adding header to csv 
        String[] columnNames = { "Error_Count", "Error_Message" };
        writer.writeNext(columnNames);
  
        // loop through DataTable 
        List<String[]> EL = errorLog.getLog();
        for(int i = 0; i < EL.size(); i++) {
        	writer.writeNext(EL.get(i)); 
        }
        
        // closing writer connection 
        writer.close(); 
	}
	
	// Make CSV File out of Error Log
	public void CSVdataWriter() throws IOException 
	{ 
	    // first create file object for file placed at location 
	    // specified by filepath 
	    //File file = new File(filePath);  
        
	    // create FileWriter object with file as parameter 
		FileWriter outputfile = new FileWriter("/Users/gavst/OneDrive - cumc.columbia.edu/Computer Science/scraper_data.csv"); 
		   
  
        // create CSVWriter object filewriter object as parameter 
        CSVWriter writer = new CSVWriter(outputfile); 
  
      
        // loop through DataTable 
        List<String[]> DT = dataTable.getDataList();
        for(int i = 0; i < DT.size(); i++) {
        	writer.writeNext(DT.get(i)); 
        }
        
        // closing writer connection 
        writer.close(); 
	} 
}
