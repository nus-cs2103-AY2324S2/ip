public class EmptyTaskException extends DukeException {
    public EmptyTaskException(String message) {
        super(message);
    }

    public static void validate(String command) throws EmptyTaskException {
        if (command.isEmpty()) {
            throw new EmptyTaskException("Empty Task Given");
        }
    }
}
