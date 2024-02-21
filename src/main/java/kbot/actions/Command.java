package kbot.actions;

import java.io.IOException;
import java.lang.IndexOutOfBoundsException;

import kbot.exceptions.InvalidCommandException;
import kbot.exceptions.InvalidInputException;

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
     * @throws IOException               If writing and loading from memory.
     * @throws IndexOutOfBoundsException If the user mark/unmark/deletes off the
     *                                   array.
     * @throws InvalidInputException     If input parameters are invalid.
     * @throws InvalidCommandException   If command is invalid.
     */
    abstract public String execute()
            throws IOException, IndexOutOfBoundsException, InvalidInputException, InvalidCommandException;
}
