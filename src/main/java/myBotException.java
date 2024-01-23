public class myBotException extends Exception {
    protected String message;
    public myBotException(String message) {
        super(message);
        this.message = message;
    }
}
