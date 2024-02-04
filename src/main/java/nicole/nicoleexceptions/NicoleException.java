package nicole.nicoleexceptions;

public class NicoleException extends Exception {
    private final String ERROR_MESSAGE;
    public NicoleException(String errorMessage) {
        this.ERROR_MESSAGE = errorMessage;
    }

    @Override
    public String toString() {
        return "ERROR. " + ERROR_MESSAGE;
    }
}
