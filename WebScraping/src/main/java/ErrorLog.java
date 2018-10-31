import java.util.ArrayList;
import java.util.List;

public class ErrorLog {
	List<String[]> log;	
	
	public ErrorLog() {
		this.log = new ArrayList<String[]>();
	}
	
	public void addError(String errorMessage) {
		String[] error = new String[0];
		error[0] = errorMessage;
		log.add(error);
	}
	
	public List<String[]> getLog() {
		return log;
	}
}
