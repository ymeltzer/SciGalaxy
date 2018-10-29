
import java.util.ArrayList;
import java.util.List;

public class DataTable {

    // Table 
	List<String[]> table = new ArrayList<String[]>();
	
    //Table Title
    String tableTitle;
    
    //Constructor
    public DataTable(String tableName) {
    	setTableName(tableName);
    	
    	// Column Names, 13 Columns
        String[] columnNames = { "Title", "Authors", "Journal", "Journal_Page", 
        		"Publication_Date", "PubMed_URL", "Journal_URL", "DOI", "PMID", "PMCID", 
        		"Keywords", "MeSH_Keywords", "References" };
        
        // Table Code
        // 1 = Title
        // 2 = Authors
        // 3 = Journal
        // 4 = Journal_Page
        // 5 = Publication_Date
        // 6 = PubMed_URL
        // 7 = Journal_URL
        // 8 = DOI
        // 9 = PMID
        // 10 = PMCID
        // 11 = Keywords
        // 12 = MeSH_Keywords
        // 13 = References
        
        table.add(columnNames); 

    }
    
    public void setTableName(String tableName) {
    	this.tableTitle = tableName;
    }
    
    public String getTitle() {
    	return this.tableTitle;
    }
    
    public void addRow(String[] row) {
    	this.table.add(row);
    }
 }
