package tyler.exception;

public class WrongInputFormatException extends TylerException {
    public WrongInputFormatException() {
        super("OHNO NO NO! Your format is incorrect!\n"
                + "The format should be like below:\n"
                + "deadline <name> /by <date>\n"
                + "event <name> /from <start> /to <end>");
    }
}
