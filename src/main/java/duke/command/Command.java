package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to be executed by the Duke chatbot.
 * This is an abstract class that serves as the base class for all commands.
 */
public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

    /**
     * Constructs a new Command object.
     * This constructor does not require any parameters.
     */
    public Command() {
        //do nothing
    }

    /**
     * Abstract method that executes the command, performing operations on the provided
     * TaskList, Storage, and Ui objects.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     * @return Returns a String message to be displayed to the user
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui);

    /**
     * Method returns false by default to keep the chatbot loop running.
     * Overridden methods should return true to end the program.
     *
     * @return Returns false
     */
    public boolean isExit() {
        return false;
    }
}
