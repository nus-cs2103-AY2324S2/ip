public class AronaIncompleteCommandException extends AronaException {
    public AronaIncompleteCommandException(String message) {
        super("Sorry, sensei! You are missing the " + message + " argument (>.<)");
    }
}
