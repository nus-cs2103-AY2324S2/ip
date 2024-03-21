package virtue;

public class UnknownCommandTypeException extends VirtueException {
    public UnknownCommandTypeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
