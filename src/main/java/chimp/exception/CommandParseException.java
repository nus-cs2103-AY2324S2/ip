package chimp.exception;
public class CommandParseException extends ChimpException{
    public CommandParseException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
