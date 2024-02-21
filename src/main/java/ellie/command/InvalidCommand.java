package ellie.command;

import ellie.TaskList;

/**
 * Represents a command that indicates an invalid input with an error message.
 */
public class InvalidCommand extends Command {

    private String errorMessage;

    /**
     * Constructs an InvalidCommand object with the specified error message.
     *
     * @param errorMessage The error message to be returned by this command.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Runs the InvalidCommand and returns the error message.
     * {@inheritDoc}
     *
     * @param tasklist The TaskList to be operated on by the command. This parameter is not used in this method.
     * @return The error message associated with this command.
     */
    public String runAndReturnResponse(TaskList tasklist) {
        return errorMessage;
    }

    /**
     * Checks whether this InvalidCommand is equal to another object.
     * {@inheritDoc}
     *
     * @param obj The object to compare with this InvalidCommand.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final InvalidCommand other = (InvalidCommand) obj;
        if (this.errorMessage == null) {
            return other.errorMessage != null;
        }

        if (this.errorMessage != other.errorMessage) {
            return false;
        }

        return true;
    }

}
