package BadApple.main;

public class BadAppleException extends IllegalArgumentException {
    public BadAppleException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
