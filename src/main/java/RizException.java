public class RizException extends Exception {
    String message;

    public RizException(String message) {
        super("EUGH AUWEH!!!!!!!!");
        this.message = message;
    }

    public String getMessage() {
        return super.getMessage() + " " + this.message;
    }
}