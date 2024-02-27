package panda.exception;

public class EmptyEventException extends PandaException {
    /**
     * Constructs a new EmptyEventException with a default message.
     */
    public EmptyEventException() {
        super("OOPS! The description of an event cannot be empty.");
    }

    /**
     * Constructs a new EmptyEventException with a specific message.
     * 
     * @param cat the category of the exception ("desc" for description, "date" for date).
     */
    public EmptyEventException(String cat) {
        super(
            cat.equals("desc") 
                ? "OOPS! The description of an event cannot be empty." 
                : "OOPS! Make sure you have both the from and to date to create an event."
        );
    }
}
