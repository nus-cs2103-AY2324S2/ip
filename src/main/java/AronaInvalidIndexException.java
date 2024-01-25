public class AronaInvalidIndexException extends AronaException {
    public AronaInvalidIndexException(String message) {
        super("Sorry, sensei! You only have " + message + " task"
                + (Integer.parseInt(message) == 1 ? "" : "s")
                + ". Please choose a valid task number (>.<)");
    }
}
