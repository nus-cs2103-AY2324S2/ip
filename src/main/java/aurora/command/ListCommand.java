package aurora.command;

import java.util.ArrayList;

import aurora.objects.Task;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/**
 * The ListCommand class handles the "list" command.
 */
public class ListCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /**
     * Constructor for the ByeCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui       Ui to interact with.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public String handle() {
        String message = "Command not executed.";
        ArrayList<Task> arrayFormList = this.taskList.getTaskList();
        message = this.ui.getTaskListString(arrayFormList);
        return message;
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
