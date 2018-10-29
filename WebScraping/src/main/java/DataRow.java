import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataRow {
	// Table 
	String[] row = new String[13];
	
	public DataRow() {
		// Row Code
        // 0 = Title
        // 1 = Authors
        // 2 = Journal
        // 3 = Journal_Page
        // 4 = Publication_Date
        // 5 = PubMed_URL
        // 6 = Journal_URL
        // 7 = DOI
        // 8 = PMID
        // 9 = PMCID
        // 10 = Keywords
        // 11 = MeSH_Keywords
        // 12 = References
		
	}
	
	public String[] getRow() {
		return row;
	}
	
	public void setTitle(String data) {
		row[0] = data;
	}
	
	public Boolean isTitleEmpty() {
		return row[0].isEmpty() ? true : false;
	}
	
	public void setAuthors(String data) {
		row[1] = data;
	}
	
	public Boolean isAuthorsEmpty() {
		return row[1].isEmpty() ? true : false;
	}
	
	public void setJournal(String data) {
		row[2] = data;
	}
	
	public Boolean isJournalEmpty() {
		return row[2].isEmpty() ? true : false;
	}
	
	public void setJournalPage(String data) {
		row[3] = data;
	}
	
	public Boolean isJournalPageEmpty() {
		return row[3].isEmpty() ? true : false;
	}
	
	public void setPublicationDate(String data) {
		row[4] = data;
	}
	
	public Boolean isPublicationDateEmpty() {
		return row[4].isEmpty() ? true : false;
	}
	
	public void setPubMedURL(String data) {
		row[5] = data;
	}
	
	public Boolean isPubMedURLEmpty() {
		return row[5].isEmpty() ? true : false;
	}
	
	public void setJournalURL(String data) {
		row[6] = data;
	}
	
	public Boolean isJournalURLEmpty() {
		return row[6].isEmpty() ? true : false;
	}
	
	public void setDOI(String data) {
		row[7] = data;
	}
	
	public Boolean isDOIEmpty() {
		return row[7].isEmpty() ? true : false;
	}
	
	public void setPMID(String data) {
		row[8] = data;
	}
	
	public Boolean isPMIDEmpty() {
		return row[8].isEmpty() ? true : false;
	}
	
	public void setPMCID(String data) {
		row[9] = data;
	}
	
	public Boolean isPMCIDEmpty() {
		return row[9].isEmpty() ? true : false;
	}
	
	public void setKeywords(String data) {
		row[10] = data;
	}
	
	public Boolean isKeywordsEmpty() {
		return row[10].isEmpty() ? true : false;
	}
	
	public void setMeSHKeywords(String data) {
		row[11] = data;
	}
	
	public Boolean isMeSHKeywordsEmpty() {
		return row[11].isEmpty() ? true : false;
	}
	
	public void setReferences(String data) {
		row[12] = data;
	}
	
	public Boolean isReferencesEmpty() {
		return row[12].isEmpty() ? true : false;
	}
}
