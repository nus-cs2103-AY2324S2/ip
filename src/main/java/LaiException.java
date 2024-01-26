public class LaiException extends Exception {
    public LaiException (String message) {
        super(message);
    }

    @Override
    public String toString() {
        return String.format("Error occurred: " + getMessage());
    }
}
