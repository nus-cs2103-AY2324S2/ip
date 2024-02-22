public class OrkException extends Exception{
    protected String message;

    public OrkException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
