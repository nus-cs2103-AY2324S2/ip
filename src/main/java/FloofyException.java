public class FloofyException extends Exception {
    public FloofyException(String message) {
        super("You have input an invalid command! " + message);
    }
}
