import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PMCAbstract {
	DataRow DataRow;
	ErrorLog ErrorLog;
	
	public static void main(String[] args) throws IOException {
        ErrorLog errorLog = new ErrorLog();
		PMCAbstract PA = new PMCAbstract(errorLog);
		PA.scrapePMCAbstract("https://www.ncbi.nlm.nih.gov/pubmed/30348987");
    }
	
	public PMCAbstract(ErrorLog errorlog) {
		ErrorLog = errorlog;
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
    		String journalURL = getAbstractData(url, document);
    		
            // Get rest of Data from Journal
            JournalSelector js = new JournalSelector(journalURL, DataRow, ErrorLog);
            DataRow = js.getJournalData();
            ErrorLog = js.getErrorLog();
    	}
    	catch (Exception e) {
    		ErrorLog.addError("Error: HTML Document Not found for " + url);
    	}
        
        // Convert RowData to array
        String[] row = DataRow.getRow();
        
        //Print DataRow
        Arrays.stream(row).forEach(System.out::println);
        System.out.println();
        
        return row;
    }
    
    public String getAbstractData(String url, Document document) {
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
        String journal = document.select(".cit").get(0).text();
        journal = journal.split("\\.")[0];
        DataRow.setJournal(journal);
        
        // Get Publication Date
        String publicationDate = document.select(".cit").get(0).text();
        publicationDate = publicationDate.split("\\. ")[1];
        if(publicationDate.contains(":")) {
        	publicationDate = publicationDate.split(":")[0];
        }
        if(publicationDate.contains(";")) {
        	publicationDate = publicationDate.split(";")[0];
        }
        DataRow.setPublicationDate(publicationDate);
        
        // Save PubMed URL
        DataRow.setPubMedURL(url);
        
        // Get Journal URL
        String journalURL = document.select("#EntrezForm > div:nth-child(1) > div.supplemental.col.three_col.last > div > div.icons.portlet > a").attr("href");
        DataRow.setJournalURL(journalURL);
        
        // Get DOI
        String DOI = document.select(".rprtid").get(0).text();
        DOI = DOI.split("DOI: ")[1];
        DataRow.setDOI(DOI);
        
        // Get PMID
        String PMID = document.select(".rprtid").get(0).text();
        PMID = PMID.split(" ")[1];
        DataRow.setPMID(PMID);
        
        // Get PMCID
        try {
        	String PMCID = document.select(".rprtid").get(0).text();
            PMCID = PMCID.split(" ")[3];
            DataRow.setPMCID(PMCID);
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
        
        return journalURL;     
    }
    
    public ErrorLog getErrorLog() {
    	return ErrorLog;
    }
}
