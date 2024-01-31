package leto.tasklist;

import static leto.ui.Ui.letoSpeak;

public class InvalidTaskException extends Exception {
    private final String message;

    public InvalidTaskException(String message) {
        this.message = message;
    }
    public void printException() {
        letoSpeak("We have a problem! " + this.message);
    }
}
