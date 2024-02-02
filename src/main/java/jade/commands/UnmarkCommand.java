package jade.commands;

import jade.data.Task;
import jade.data.TaskList;
import jade.exception.JadeException;
import jade.ui.Ui;
import jade.storage.Storage;

/**
 * The <code>UnmarkCommand</code> object represents the command to mark a task as not done yet.
 */
public class UnmarkCommand extends Command {
    private int index; // the index of the task to be unmarked

    /**
     * Class constructor specifying the index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * @inheriDocs This implementation prints an unmark message after the task is unmarked.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JadeException {
        if (index <= 0 || index > taskList.size()) {
            throw new JadeException("\tPlease input a valid number to unmark done.");
        }
        taskList.unmark(index-1);
        ui.printMessage(String.format("\tNice, I've marked this task as not done yet:\n\t  %s",  taskList.get(index-1)));
    }

    /**
     * @inheriDocs The UnmarkCommand does not indicate the exit of the program.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
