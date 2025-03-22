package duke.exceptions;

/**
 * Class to indicate EmptyDescription when creating task
 */
public class EmptyDescriptionException extends DukeException {
    private String commandType;

    /**
     * Constructor for EmptyDescriptionException
     * @param commandType to indicate the task with empty description
     */
    public EmptyDescriptionException(String commandType) {
        super();
        this.commandType = commandType;
    }

    @Override
    public String toString() {
        return String.format("You definitely are a genius creating a %s with no description.\n"
                + "I hope you are genius enough "
                + "to try again with an adequate description\n", this.commandType);
    }
}
