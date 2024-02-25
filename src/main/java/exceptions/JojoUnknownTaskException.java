package exceptions;

public class JojoUnknownTaskException extends JojoException {
    public JojoUnknownTaskException() {
        super("Sorry, but this task is unknown to me. Please try again using any of the following keywords: 'todo', 'deadline' or 'event'.");
    }
}
