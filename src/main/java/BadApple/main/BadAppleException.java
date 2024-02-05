package BadApple.main;

/**
 * Any exception that arises from the use of BadApple's classes
 * throws a BadAppleException. Good use cases are
 * when a user supplies bad input to a Task.
 */
public class BadAppleException extends IllegalArgumentException {
    public BadAppleException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
