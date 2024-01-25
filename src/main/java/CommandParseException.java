public class CommandParseException extends ChimpException{
    CommandParseException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
