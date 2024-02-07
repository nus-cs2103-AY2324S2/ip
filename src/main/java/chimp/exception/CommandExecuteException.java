package chimp.exception;

public class CommandExecuteException extends ChimpException {

    public CommandExecuteException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
