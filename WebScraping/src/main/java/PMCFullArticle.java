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
		this.DataRow = DataRow;
	}
	
	//for articles of the type
    //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/
    //PMC free full article
    public DataRow scrapePMCFull(String url) throws IOException {

    	// Get HTML of WebPage
    	final Document document = Jsoup.connect(url).get();
        
    	// Get Title
    	if (DataRow.isTitleEmpty()) {
    		String title = document.select(".content-title").get(0).text();
        	DataRow.setTitle(title);
        }
    	
        // Get Authors
        if(DataRow.isAuthorsEmpty()) {
        	String authors = document.select(".fm-author").get(0).text();
            DataRow.setAuthors(authors);
        }
        
        // Get Journal
        if(DataRow.isJournalEmpty()) {
        	String journal = document.select(".cit").get(0).text();
            journal = journal.split("\\.")[0];
            DataRow.setJournal(journal);
        }
        
        // Get Publication Date
        if(DataRow.isPublicationDateEmpty()) {
        	String publicationDate = document.select(".fm-vol-iss-date").get(0).text();
            publicationDate = publicationDate.split(" ", 2)[1].split(" ", 2)[1].split("\\.")[0];
            DataRow.setPublicationDate(publicationDate);
        }

        //Get DOI
        if(DataRow.isDOIEmpty()) {
            String DOI = document.select(".doi").get(0).text();
            DOI = DOI.split("\\[")[1].split("\\]")[0];
            DataRow.setDOI(DOI);
        }
        
        // Get PMID
        if(DataRow.isPMIDEmpty()) {
            String PMID = document.select(".fm-citation-pmid").get(0).text();
            PMID = PMID.split(" ")[1];
            DataRow.setPMID(PMID);	
        }


        // Get PMCID
        if(DataRow.isPMCIDEmpty()) {
            String PMCID = document.select(".fm-citation-pmcid").get(0).text();
            PMCID = PMCID.split(" ")[1];
            DataRow.setPMCID(PMCID);
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
        
        // Convert DataRow to Stringp[] for printing
        String[] row = DataRow.getRow();
        
        //Print DataRow
        Arrays.stream(row).forEach(System.out::println);
        System.out.println();
        
        return DataRow;
    }
}
