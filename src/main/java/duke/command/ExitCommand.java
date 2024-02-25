package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the Duke chatbot program.
 * This class extends the Command class and overrides the execute method to print a closing message.
 * Also overrides the isExit() method to exit the program loop.
 */
public class ExitCommand extends Command {
    /**
     * Constructs a new ExitCommand object.
     * This constructor does not require any parameters.
     */
    public ExitCommand() {
        //do nothing
    }

    /**
     * Returns a message signalling the closing of the program.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        return ui.printMessage("Fair winds to ye, me hearty! "
                + "May the tide carry ye safely until our paths cross again.");
    }

    /**
     * Returns boolean signalling for program to exit.
     *
     * @return Returns true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
