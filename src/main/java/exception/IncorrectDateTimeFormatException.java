package exception;

public class IncorrectDateTimeFormatException extends Exception{
    public IncorrectDateTimeFormatException() {
        super("IncorrectDateTimeFormatException: "
                + "only accepts 'DD/MM/YYYY TTTT' format");
    }
}
