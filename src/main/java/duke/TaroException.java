package duke;

/**
 * Class that represents a Taro-specific exception
 */
public class TaroException extends Exception {
    public TaroException() {
        super();
    }
    public TaroException(String message) {
        super(message);
    }
}
