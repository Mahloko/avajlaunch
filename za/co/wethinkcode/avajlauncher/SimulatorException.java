package za.co.wethinkcode.avajlauncher;

public class SimulatorException extends Exception {
	public SimulatorException(String reason, String statement) {
		super(reason + ": " + statement);
	}

	public SimulatorException(String reason, String statement, Throwable cause) {
		super(reason + ": " + statement, cause);
	}
}