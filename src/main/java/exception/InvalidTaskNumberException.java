package exception;

public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(String s) {
        super(String.format("Invalid task number %s. Please try again.", s));
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
