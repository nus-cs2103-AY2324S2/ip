package duke.exception;

public class DateFormatException extends Exception {
    public DateFormatException() {

        super("Please check the format of your date input! Accepted format: DD/MM/YYYY TTTT");
    }
}
