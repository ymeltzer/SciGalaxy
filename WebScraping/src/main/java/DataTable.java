
import java.util.ArrayList;
import java.util.List;

public class DataTable {

    // Table 
	List<String[]> table = new ArrayList<String[]>();
    
    //Constructor
    public DataTable() {
    	
    	// Column Names, 13 Columns
        String[] columnNames = { "Title", "Authors", "Journal", 
        		"Publication_Date", "PubMed_URL", "Journal_URL", "DOI", "PMID", "PMCID", 
        		"Keywords", "MeSH_Keywords", "References", "Source" };
        
        // Table Code
        // 1 = Title
        // 2 = Authors
        // 3 = Journal
        // 4 = Publication_Date
        // 5 = PubMed_URL
        // 6 = Journal_URL
        // 7 = DOI
        // 8 = PMID
        // 9 = PMCID
        // 10 = Keywords
        // 11 = MeSH_Keywords
        // 12 = References
        // 13 = Source
        
        table.add(columnNames); 
    }
    
    public void addRow(String[] row) {
    	this.table.add(row);
    }
    
    public List<String[]> getDataList() {
    	return table;
    }
 }
