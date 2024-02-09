package duke.command;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {
    protected TaskList tasks;
    protected Storage storage;
    protected Ui ui;

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
     */
    public abstract void execute(TaskList tasks, Storage storage, Ui ui);

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
