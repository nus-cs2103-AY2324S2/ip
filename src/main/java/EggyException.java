public class EggyException extends Exception {
    public EggyException(String message) {
        super("OOPS!!!" + message);
    }
}