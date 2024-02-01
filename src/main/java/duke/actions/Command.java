package duke.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidInputException;

/**
 * A command abstract class is the parent of all types of commands.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public abstract class Command {
    /** Empty constructor */
    public Command() {
    }

    /**
     * Executes the task.
     * 
     * @return String to show if the task has been ran successfully.
     * @throws IOException               Exception for writing and loading from
     *                                   memory.
     * @throws IndexOutOfBoundsException Exception when the user mark/unmark/deletes
     *                                   off the array.
     * @throws InvalidInputException     Exception when input parameters are
     *                                   invalid.
     * @throws InvalidCommandException   Exception when command is invalid.
     */
    abstract public String execute()
            throws IOException, IndexOutOfBoundsException, InvalidInputException, InvalidCommandException;
}
