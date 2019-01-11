import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PMCFullArticle {
	
	DataRow DataRow = new DataRow();
	ErrorLog ErrorLog;
	
	public static void main(String[] args) throws IOException {
		ErrorLog errorLog = new ErrorLog();
		DataRow datarow = new DataRow();
		PMCFullArticle PFA = new PMCFullArticle(datarow, errorLog);
		PFA.scrapePMCFull("https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/");
    }
	
	public PMCFullArticle(DataRow dataRow, ErrorLog errorLog) {
		this.ErrorLog = errorLog;
		this.DataRow = dataRow;
	}
	
	//for articles of the type
    //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/
    //PMC free full article
    public DataRow scrapePMCFull(String url) throws IOException {

    	// Get HTML of WebPage
    	try {
        	final Document document = Jsoup.connect(url).get();
            
        	DataRow = getPMCdata(document, url); 
    	}
    	catch (Exception e) {
    		ErrorLog.addError("Error: HTML Document Not found for " + url);
    	}
        
        //Print DataRow
        //String[] row = DataRow.getRow();
        //Arrays.stream(row).forEach(System.out::println);
        //System.out.println();
        
        return DataRow;
    }
    
    public DataRow getPMCdata(Document document, String url) {
    	// Get Title
    	try {
    		if (DataRow.isTitleEmpty()) {
        		String title = document.select(".content-title").get(0).text();
            	DataRow.setTitle(title);
            }
    	}
    	catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Title found in PMC Article of" + url);
        }
    	
        // Get Authors
        try {
        	if(DataRow.isAuthorsEmpty()) {
            	String authors = document.select(".fm-author").get(0).text();
                DataRow.setAuthors(authors);
            }
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Authors found in PMC Article of" + url);
        }
        
        // Get Journal
        try {
        	if(DataRow.isJournalEmpty()) {
            	String journal = document.select(".cit").get(0).text();
                journal = journal.split("\\.")[0];
                DataRow.setJournal(journal);
            }	
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Journal found in PMC Article of" + url);
        }
        
        // Get Publication Date
        try {
        	if(DataRow.isPublicationDateEmpty()) {
            	String publicationDate = document.select(".fm-vol-iss-date").get(0).text();
                publicationDate = publicationDate.split(" ", 2)[1].split(" ", 2)[1].split("\\.")[0];
                DataRow.setPublicationDate(publicationDate);
            }
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No Publciation Date found in PMC Article of" + url);
        }

        //Get DOI
        try {
        	if(DataRow.isDOIEmpty()) {
                String DOI = document.select(".doi").get(0).text();
                DOI = DOI.split("\\[")[1].split("\\]")[0];
                DataRow.setDOI(DOI);
            }
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No DOI found in PMC Article of" + url);
        }
        
        // Get PMID
        try {
            if(DataRow.isPMIDEmpty()) {
                String PMID = document.select(".fm-citation-pmid").get(0).text();
                PMID = PMID.split(" ")[1];
                DataRow.setPMID(PMID);	
            }	
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No PMID found in PMC Article of" + url);
        }
        
        // Get PMCID
        try {
        	if(DataRow.isPMCIDEmpty()) {
                String PMCID = document.select(".fm-citation-pmcid").get(0).text();
                PMCID = PMCID.split(" ")[1];
                DataRow.setPMCID(PMCID);
            }
        }
        catch (IndexOutOfBoundsException e ) {
        	ErrorLog.addError("Error: No PMCID found in PMC Article of" + url);
        }
        
        // Set Journal URL
        if(DataRow.isJournalURLEmpty()) {
            DataRow.setJournalURL(url);
        }
        
        // Get Reference List
        if(DataRow.isReferencesEmpty()) {
            String refs = document.body().select("#reference-list" /*css selector*/).get(0).text();
            DataRow.setReferences(refs);
        }
        
        // Set Source
        String source = "PMC_Full_Article";
        DataRow.setSource(source);
    	
    	return DataRow;
    }
    
	public ErrorLog getErrorLog() {
		return this.ErrorLog;
	}
}
