package programms.project_skin;

public class UnhandledSkinSizeException extends Exception {
	public UnhandledSkinSizeException(String exceptionText) {
		super("---" + exceptionText + "---");
	}
	public UnhandledSkinSizeException() {
		this("No comment");
	}

}
