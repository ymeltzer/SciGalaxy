import java.util.ArrayList;
import java.util.List;

public class ErrorLog {
	List<String[]> log;	
	int count;
	public ErrorLog() {
		this.log = new ArrayList<String[]>();
		this.count = 0;
	}
	
	public void addError(String errorMessage) {
		String[] error = new String[2];
		error[0] = String.valueOf(count);
		this.count++;
		error[1] = errorMessage;
		log.add(error);
	}
	
	public List<String[]> getLog() {
		return log;
	}
}
