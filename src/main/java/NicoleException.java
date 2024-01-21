public class NicoleException extends Exception {
    private String errorMessage;
    public NicoleException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Nicole: ERROR. " + errorMessage;
    }
}
