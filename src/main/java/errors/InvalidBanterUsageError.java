package errors;

public class InvalidBanterUsageError extends Exception{
    public InvalidBanterUsageError(String error, String usage) {
        super(error + "\nUsage: " + usage);
    }
}
