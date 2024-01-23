public class SaopigInvaildSizeException extends Throwable {
    public SaopigInvaildSizeException(String message) {
        Saopig.speak(message);
    }

    // Constructor with a message and a cause
    public SaopigInvaildSizeException(String message, Throwable cause) {
        super();
    }
}
