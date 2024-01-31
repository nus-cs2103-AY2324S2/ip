package checkbot.exception;

public abstract class CheckbotException extends Exception {
    public CheckbotException(String err) {
        super(err);
    }
}
