package exceptions;

public class HarperInvalidDateTimeException extends HarperException {
    public HarperInvalidDateTimeException() {
        super("Date time should follow this format:\n"
                + "d/M/yyyy HH:mm (e.g., 15/9/2024 16:25)");
    }
}
