import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PMCAbstract {
	DataRow DataRow;
	ErrorLog ErrorLog;
	String journalURL;
	
	public static void main(String[] args) throws IOException {
        ErrorLog errorLog = new ErrorLog();
		PMCAbstract PA = new PMCAbstract(errorLog);
		PA.scrapePMCAbstract("https://www.ncbi.nlm.nih.gov/pubmed/30348987");
    }
	
	public PMCAbstract(ErrorLog errorlog) {
		this.ErrorLog = errorlog;
		this.DataRow = new DataRow();
	}
	
	//for articles of the type
    //https://www.ncbi.nlm.nih.gov/pubmed/30366941
	//https://www.ncbi.nlm.nih.gov/pubmed/30301401
	//https://www.ncbi.nlm.nih.gov/pubmed/30286377
	//https://www.ncbi.nlm.nih.gov/pubmed/30290272
	//https://www.ncbi.nlm.nih.gov/pubmed/30348987
    //PMC Abstract
    public String[] scrapePMCAbstract(String url) throws IOException {
    	
    	// Get HTML of WebPage
    	try {
    		final Document document = Jsoup.connect(url).get();
    		
    		// Get Data from PubMed Abstract
    		getAbstractData(url, document);
    		
            // Get rest of Data from Journal
            JournalSelector js = new JournalSelector(journalURL, DataRow, ErrorLog);
            if(journalURL != null) {
                this.DataRow = js.getJournalData();
                this.ErrorLog = js.getErrorLog();
            }

    	}
    	catch (Exception e) {
    		ErrorLog.addError("Error: HTML Document Not found for " + url);
    	}
        
        // Convert RowData to array
    	String[] row = DataRow.getRow();
        
        //Print DataRow
        //Arrays.stream(row).forEach(System.out::println);
        //System.out.println();
        
        return row;
    }
    
    public void getAbstractData(String url, Document document) {
    	// Get Title
    	try {
    		String title = document.select("#maincontent > div > div.rprt_all > div > h1").get(0).text();
        	DataRow.setTitle(title);
    	}
    	catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Title found in Abstract of" + url);
        }
    	
    	// Get Authors
    	try {
    		String authors = document.select(".auths").get(0).text();
            DataRow.setAuthors(authors);
    	}
    	catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Authors found in Abstract of" + url);
        }
        
        // Get Journal
        try {
        	String journal = document.select(".cit").get(0).text();
            journal = journal.split("\\.")[0];
            DataRow.setJournal(journal);
        }
    	catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Journal Name found in Abstract of " + url);
        }
        
        // Get Publication Date
       try {
    	   String publicationDate = document.select(".cit").get(0).text();
           publicationDate = publicationDate.split("\\. ")[1];
           if(publicationDate.contains(":")) {
           	publicationDate = publicationDate.split(":")[0];
           }
           if(publicationDate.contains(";")) {
           	publicationDate = publicationDate.split(";")[0];
           }
           DataRow.setPublicationDate(publicationDate);
       }
       catch (IndexOutOfBoundsException e ) {
       	ErrorLog.addError("Error: No Publication Date found in Abstract of " + url);
       }
        
        // Save PubMed URL
        DataRow.setPubMedURL(url);
        
        // Get Journal URL
        try {
        	 String journalURL = document.select("#EntrezForm > div:nth-child(1) > div.supplemental.col.three_col.last > div > div.icons.portlet > a").attr("href");
             DataRow.setJournalURL(journalURL);
             this.journalURL = journalURL;
        }
        catch (IndexOutOfBoundsException e ) {
           	ErrorLog.addError("Error: No Journal URL found in Abstract of " + url);
        }
        
        // Get DOI
        try {
            String DOI = document.select(".rprtid").get(0).text();
            DOI = DOI.split("DOI: ")[1];
            DataRow.setDOI(DOI);
        }
        catch (IndexOutOfBoundsException e ) {
           	ErrorLog.addError("Error: No DOI found in Abstract of " + url);
        }
        
        // Get PMID
        try {
            String PMID = document.select(".rprtid").get(0).text();
            PMID = PMID.split(" ")[1];
            DataRow.setPMID(PMID);
        }
        catch (IndexOutOfBoundsException e ) {
           	ErrorLog.addError("Error: No PMID found in Abstract of " + url);
        }
        
        // Get PMCID
        try {
        	String PMCID = document.select(".rprtid").get(0).text();
            PMCID = PMCID.split(" ")[3];
            if(PMCID.contains("PMC")) {
            	DataRow.setPMCID(PMCID);
            }
            else {
            	ErrorLog.addError("Error: No PMCID found in Abstract of " + url);
            }
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No PMCID found in Abstract of " + url);
        }
        
        // Get Keywords
        try {
        	String keywords = document.select(".keywords").get(0).text();
            keywords = keywords.split(": ")[1];
            DataRow.setKeywords(keywords);
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No keywords found in Abstract of " + url);
        }
        
        // Set Source
        String source = "PMC_Abstract";
        DataRow.setSource(source);
        
        // Check for PMC Full Article
        if(!DataRow.isPMCIDEmpty()) {
        	String PMCID = DataRow.getPMCID();
        	journalURL = "https://www.ncbi.nlm.nih.gov/pmc/articles/" + PMCID;
        }
             
    }
    
    public ErrorLog getErrorLog() {
    	return ErrorLog;
    }
}
