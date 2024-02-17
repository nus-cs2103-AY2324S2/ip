package shuheng.exceptions;

/**
 * This class represents exceptions thrown by Duke, our friendly chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
