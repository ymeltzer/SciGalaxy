import java.io.IOException;

public class JournalSelector {
    DataRow DataRow;
	ErrorLog errorLog;
    
	public JournalSelector(String journalURL, DataRow row, ErrorLog errorLog) throws IOException {
    	this.DataRow = row;
    	this.errorLog = errorLog;
    	
		// Check for PMC Full Article (PMC)
    	if(journalURL.contains("PMC")) {
			PMCFullArticle PFA = new PMCFullArticle(row, errorLog);
			this.DataRow = PFA.scrapePMCFull(journalURL);
			this.errorLog = PFA.getErrorLog();
		}
    	
    	// Check Elsevier Article
    	
    	// Check Nature Article
    	
    	// ...
    }
	
	public DataRow getJournalData() {
		return this.DataRow;
	}
	
	public ErrorLog getErrorLog() {
		return this.errorLog;
	}
}
