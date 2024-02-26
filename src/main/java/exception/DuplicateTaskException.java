package exception;

public class DuplicateTaskException extends Exception {

    public DuplicateTaskException() {
        super("A duplicate task exists in store!");
    }

    @Override
    public String toString() {
        return super.getMessage();
    }

}
