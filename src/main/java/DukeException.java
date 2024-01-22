public class DukeException extends Exception {
    private String exceptionMessage;
    public DukeException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
}
