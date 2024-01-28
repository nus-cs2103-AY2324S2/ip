package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskCreationException;
import duke.exceptions.TaskModificationException;

import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * Abstact class that implements commands, used for the control flow of the bot.
 * 
 * @author delishad21
 */
public abstract class Command {
    
    public boolean isExit;

    /**
     * Basic constructor for command.
     * 
     * @param isExit for determining if the command leads to an exit.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Abstract execute method left for inheriting commands to implement.
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
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) 
    throws TaskCreationException, DateTimeParseException, IndexOutOfBoundsException,
    TaskModificationException, NumberFormatException, IOException;

}