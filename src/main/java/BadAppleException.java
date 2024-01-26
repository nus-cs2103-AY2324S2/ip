public class BadAppleException extends IllegalArgumentException {
    BadAppleException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
