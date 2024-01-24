public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void validate(String command) throws DukeException {
        if (!Duke.commands.contains(command)) {
            throw new DukeException("Invalid Command Given");
        }
    }

}
