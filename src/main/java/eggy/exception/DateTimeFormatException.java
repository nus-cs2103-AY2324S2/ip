package eggy.exception;

public class DateTimeFormatException extends EggyException {
    public DateTimeFormatException() {
        super(" Invalid datetime format. Please use the format d/MM/yyyy HHmm.");
    }
}
