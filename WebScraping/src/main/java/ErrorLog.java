import java.util.ArrayList;

public class ErrorLog {
	ArrayList<String> log;	
	
	public ErrorLog() {
		this.log = new ArrayList<String>();
	}
	
	public void addError(String errorMessage) {
		log.add(errorMessage);
	}
	
	public ArrayList<String> getLog() {
		return log;
	}
}
