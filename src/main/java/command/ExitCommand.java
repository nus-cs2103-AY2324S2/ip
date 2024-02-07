package command;

import task.TaskList;
import utilities.Storage;
import ui.Ui;

/**
 * Controls what to do when user exits program.
 */
public class ExitCommand extends Command {
    /**
     * ExitCommand class constructor.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Executes the exit program process.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the command is applied.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     * @return The response expected from the chatbot.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        return "Bye! Hope to see you again!";
    }
}
