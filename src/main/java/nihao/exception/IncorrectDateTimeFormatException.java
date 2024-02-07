package nihao.exception;

public class IncorrectDateTimeFormatException extends Exception{
    public IncorrectDateTimeFormatException() {
        super("IncorrectDateTimeFormatException: "
                + "only accepts 'dd/MM/yyyy HHmm' format");
    }
}
