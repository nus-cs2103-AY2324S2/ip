package exception;

public class InvalidDateException extends DukeException {
    public InvalidDateException() {
        super("Please use date format d/M/yyyy HHmm\neg 30/10/2024 1800");
    }
}
