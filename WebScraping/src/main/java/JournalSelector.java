


public class JournalSelector {
    DataRow DataRow;
	ErrorLog errorLog;
    
	public JournalSelector(String journalURL, DataRow row, ErrorLog errorLog) {
    	this.DataRow = row;
    	this.errorLog = errorLog;
		
		//if(journalURL.contains())
    }
	
	public DataRow getJournalData() {
		return this.DataRow;
	}
	
	public ErrorLog getErrorLog() {
		return this.errorLog;
	}
}
