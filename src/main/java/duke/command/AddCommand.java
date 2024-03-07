package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
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
        assert task != null;
        this.task = task;
    }

    /**
     * Execute the addition of Tasks to TaskList.
     * Stores updated task list to file store
     *
     * @param taskList the task list
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.add(this.task);
        storage.Store(taskList.toString());
        int count = taskList.getCountByType (this.task.getTypeOfTask());
        String sb = "Got it. I've added this task:\n" +
                this.task.printOutput() +
                "\nNow you have " + count + " tasks in the list";
        return sb;
    }
}
