package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;
import duke.exceptions.TaskModificationException;

import duke.utils.TaskList;
import duke.utils.Storage;

/**
 * Abstact class that implements commands, used for the control flow of the bot.
 *
 * @author delishad21
 */
public abstract class Command {

    public boolean isExit;

    /**
     * Creates a command object.
     *
     * @param isExit for determining if the command leads to an exit.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes command, left as abstract for inheriting classes to implement.
     *
     * @param tasks the current list of tasks
     * @param ui Ui object used by bot for printing information
     * @param storage Storage object with save file.
     * @throws TaskCreationException
     * @throws DateTimeParseException
     * @throws IndexOutOfBoundsException
     * @throws TaskModificationException
     * @throws NumberFormatException
     * @throws IOException
     */
    public abstract String execute(TaskList tasks, Storage storage)
    throws TaskCreationException, DateTimeParseException, IndexOutOfBoundsException,
    TaskModificationException, NumberFormatException, IOException;

}