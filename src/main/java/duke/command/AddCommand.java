package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.task.Task;

/**
 * The type Add command.
 * For adding commands to TaskList
 */
public class AddCommand extends Command{
    private Task task;

    /**
     * Instantiates a new Add command.
     */
    public AddCommand(){
    }

    /**
     * Instantiates a new Add command.
     *
     * @param task the Task object
     */
    public AddCommand(Task task){
        this.task = task;
    }

    /**
     * Execute the addition of Tasks to TaskList.
     * Stores updated task list to file store
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        taskList.add(this.task);
        storage.Store(taskList.toString());
        int count = taskList.getTaskList().size();
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:");
        sb.append("\n").append(this.task.printOutput());
        sb.append("\n").append("Now you have "+count+" tasks in the list");
        ui.setCommandOutput(sb.toString());
    }
}
