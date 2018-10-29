import java.io.IOException;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PMCFullArticle {
	
	static DataRow DataRow = new DataRow();
	
	public static void main(String[] args) throws IOException {
        scrapePMCFull("https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/");
    }
	
	//for articles of the type
    //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/
    //PMC free full article
    public static String[] scrapePMCFull(String url) throws IOException {

        // Get Title
    	final Document document = Jsoup.connect(url).get();
        DataRow.setTitle(document.title());

        // Get Authors
        String authors = document.select(".fm-author").get(0).text();
        DataRow.setAuthors(authors);
        
        // Get Journal
        String journal = document.select("#maincontent > div.navlink-box > ul > li.article-entrez-filter > a").get(0).text();
        DataRow.setJournal(journal);
        
        // Get Journal Page
        
        
        // Get Publication Date
        String publicationDate = document.select(".fm-vol-iss-date").get(0).text();
        DataRow.setPublicationDate(publicationDate);

        //Get DOI
        String DOI = document.select(".doi").get(0).text();
        DataRow.setDOI(DOI);
        
        // Get PMID
        String PMID = document.select(".fm-citation-pmid").get(0).text();
        DataRow.setPMID(PMID);

        // Get PMCID
        String PMCID = document.select(".fm-citation-pmcid").get(0).text();
        DataRow.setPMCID(PMCID);

        // Get PubMed URL
        String PubMedURL = document.select("head > link:nth-child(17)").get(0).baseUri();
        DataRow.setPubMedURL(PubMedURL);
        
        // Get Reference List
        String refs = document.body().select("#reference-list" /*css selector*/).get(0).text();
        DataRow.setReferences(refs);
        
        //Return DataRow
        String[] row = DataRow.getRow();
        
        //Print DataRow
        Arrays.stream(row).forEach(System.out::println);
        System.out.println();
        
        return row;
    }
}
