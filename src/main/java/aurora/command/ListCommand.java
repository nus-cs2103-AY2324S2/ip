package aurora.command;

import aurora.objects.Task;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

import java.util.ArrayList;

/**
 * The ListCommand class handles the "list" command.
 */
public class ListCommand extends Command {

    /** TaskList to interact with. */
    private TaskList taskList;

    /** Ui to interact with. */
    private Ui ui;

    /** Storage to interact with. */
    private Storage storage;

    /**
     * Constructor for the ByeCommand class.
     *
     * @param taskList TaskList to edit.
     * @param ui Ui to interact with.
     * @param storage Storage to interact with.
     */
    public ListCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public void handle() {
        ArrayList<Task> arrayFormList = this.taskList.getTaskList();
        this.ui.printTaskList(arrayFormList);
    }

    @Override
    public String handleGui() {
        String message = "Command not executed.";
        ArrayList<Task> arrayFormList = this.taskList.getTaskList();
        message = this.ui.getTaskListString(arrayFormList);
        assert !(message.equals("Command not executed.")) : "List command not executed.";
        return message;
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
