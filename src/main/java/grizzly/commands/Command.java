package grizzly.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import grizzly.exceptions.GrizzlyException;
import grizzly.utils.Database;
import grizzly.utils.Storage;
/**
 * Abstact class that implements commands, used for the control flow of the bot.
 *
 * @author delishad21
 */
public abstract class Command {

    private boolean isExit;

    /**
     * Creates a command object.
     *
     *
     * @param isExit for determining if the command leads to an exit.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns if the command is an exit command
     *
     * @return boolean to indicate if the command is exiting
     */
    public boolean isExitCommand() {
        return this.isExit;
    }

    /**
     * Executes command, left as abstract for inheriting classes to implement.
     *
     * @param db the current database of records.
     * @param storage Storage object with save file.
     * @throws GrizzlyException
     * @throws DateTimeParseException
     * @throws IOException
     */
    public abstract String execute(Database db, Storage storage)
            throws GrizzlyException, DateTimeParseException, IOException;

}
