public class MissingDeadlineException extends DukeException {
    public MissingDeadlineException(String message) {
        super(message);
    }

    public static void validate(String[] tokens) throws MissingDeadlineException {
        if (tokens.length != 2) {
            throw new MissingDeadlineException("No Deadline Given");
        }
    }
}
