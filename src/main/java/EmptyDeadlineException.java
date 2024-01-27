public class EmptyDeadlineException extends PandaException {
    public EmptyDeadlineException() {
        super("OOPS! The description of a deadline cannot be empty.");
    }

    public EmptyDeadlineException(String cat) {
        super(
            cat.equals("desc") 
                ? "OOPS! The description of a deadline cannot be empty." 
                : "OOPS! The deadline of a deadline cannot be empty."
        );
    }
}
