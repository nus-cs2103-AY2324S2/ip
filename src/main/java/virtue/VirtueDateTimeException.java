package virtue;

public class VirtueDateTimeException extends VirtueException {
    public VirtueDateTimeException(String dateType, String type) {
        super("The \"" + dateType + "\" date of a " + type
                + " command must be in a valid date and time format.");
    }
}
