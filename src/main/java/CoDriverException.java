public class CoDriverException extends Exception {
    public CoDriverException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
