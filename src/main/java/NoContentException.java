/**
 * NoContentException alerts users when they did not provide task content.
 */
public class NoContentException extends Exception {
    public NoContentException() {
        super("I am sorry, can I know the content of the task?");
    }
}
