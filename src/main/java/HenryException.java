public class HenryException extends Exception {
    HenryException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Whoops... " + this.getMessage();
    }
}