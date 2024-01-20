public class DukeException extends Exception{
    private final String displayMessage;
    public DukeException(String errorMessage, String displayMessage) {
        super(errorMessage);
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return this.displayMessage;
    }
}
