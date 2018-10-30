import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class PMCAbstract {
	static DataRow DataRow = new DataRow();
	
	public static void main(String[] args) throws IOException {
        scrapePMCAbstract("https://www.ncbi.nlm.nih.gov/pubmed/30348987");
    }
	
	//for articles of the type
    //https://www.ncbi.nlm.nih.gov/pubmed/30366941
	//https://www.ncbi.nlm.nih.gov/pubmed/30301401
	//https://www.ncbi.nlm.nih.gov/pubmed/30286377
	//https://www.ncbi.nlm.nih.gov/pubmed/30290272
	//https://www.ncbi.nlm.nih.gov/pubmed/30348987
    //PMC Abstract
    public static String[] scrapePMCAbstract(String url) throws IOException {
    	// Get HTML of WebPage
    	final Document document = Jsoup.connect(url).get();
    	
    	// Get Title
    	String title = document.select("#maincontent > div > div.rprt_all > div > h1").get(0).text();
    	DataRow.setTitle(title);
    	
    	// Get Authors
    	String authors = document.select(".auths").get(0).text();
        DataRow.setAuthors(authors);
        
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
        	//arraylist.add("Error: No key words in Abstract" + url));
        }
        
        // Get Keywords
        try {
        	String keywords = document.select(".keywords").get(0).text();
            keywords = keywords.split(": ")[1];
            DataRow.setKeywords(keywords);
        }
        catch (IndexOutOfBoundsException e ) {
        	//arraylist.add("Error: No key words in Abstract" + url));
        }
        
        
        // Set Source
        String source = "PMC_Abstract";
        DataRow.setSource(source);
        
        // Enter Full Text Article for References and Missing Information
        DataRow = journalSelector(journalURL, DataRow);
        
        //Return DataRow
        String[] row = DataRow.getRow();
        
        //Print DataRow
        Arrays.stream(row).forEach(System.out::println);
        System.out.println();
        
        return row;
    }
    
    public static DataRow journalSelector(String url, DataRow row) {
    	if(url.contains())
    	return row;
    }
}
