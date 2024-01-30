public abstract class TyroneException extends Exception {
    public TyroneException(String message) {
        super(String.format(Messages.MESSAGE_ERROR, message));
    }
}
