public class EmptyEventException extends PandaException {
    public EmptyEventException() {
        super("OOPS! The description of an event cannot be empty.");
    }

    public EmptyEventException(String cat) {
        super(
            cat.equals("desc") 
                ? "OOPS! The description of an event cannot be empty." 
                : "OOPS! Make sure you have both the from and to date to create an event."
        );
    }
}
