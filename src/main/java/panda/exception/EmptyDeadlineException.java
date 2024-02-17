package panda.exception;

public class EmptyDeadlineException extends PandaException {
    /**
     * Constructs a new EmptyDeadlineException with a default message.
     */
    public EmptyDeadlineException() {
        super("OOPS! The description of a deadline cannot be empty.");
    }

    /**
     * Constructs a new EmptyDeadlineException with a specific message.
     * 
     * @param cat the category of the exception ("desc" for description, "date" for date).
     */
    public EmptyDeadlineException(String cat) {
        super(
            cat.equals("desc") 
                ? "OOPS! The description of a deadline cannot be empty." 
                : "OOPS! The deadline of a deadline cannot be empty."
        );
    }
}
