package exception;

public class InvalidDateTimeException extends DukeException {

    private String message;

    public InvalidDateTimeException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return "    Please provide the time in this format: 'yyyy-mm-dd hh:mm'\n"
                + "    ------------------------------------------------\n";
    }
}
