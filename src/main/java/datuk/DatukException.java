package datuk;

/**
 * A custom throwable exception class that returns a specified error message.
 */

public class DatukException extends Exception {
    public DatukException(String err) {
        super(err);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
