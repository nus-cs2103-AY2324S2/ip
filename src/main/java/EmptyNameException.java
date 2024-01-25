public class EmptyNameException extends Exception {
    public EmptyNameException() {
        super("OHNO NO NO! The name of the task or event cannot be empty!");
    }
}
