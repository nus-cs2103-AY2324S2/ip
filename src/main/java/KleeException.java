public class KleeException extends Exception {
    public KleeException(String errorMessage) {
        super(errorMessage);
    }

    public String NoDescriptionError() {
        return "";
    }
}
