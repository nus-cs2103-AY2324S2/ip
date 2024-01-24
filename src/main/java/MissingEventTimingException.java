public class MissingEventTimingException extends DukeException {
    public MissingEventTimingException(String message) {
        super(message);
    }

    public static void validate(String[] tokens) throws MissingEventTimingException {
        if (tokens.length != 3) {
            throw new MissingEventTimingException("No Deadline Given");
        }
    }
}
