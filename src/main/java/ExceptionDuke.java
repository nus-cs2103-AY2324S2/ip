public class ExceptionDuke extends Exception {

    public ExceptionDuke(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}