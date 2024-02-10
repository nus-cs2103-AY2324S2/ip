package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import tasks.ToDo;

/**
 * Represents a command to add a new ToDo task to the task list.
 * Extends the Command class and implements the execute method to perform the addition operation.
 */
public class TodoCommand extends Command {
    private String taskDescription;

    /**
     * Constructs a TodoCommand with the specified description for the ToDo task.
     *
     * @param taskDescription The description of the ToDo task to be added.
     */
    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command to add a new ToDo task to the task list by creating a new ToDo object,
     * adding it to the TaskList, and then saving the updated task list to the storage.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     * @return A UserCommand indicating the result of the addition operation.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo todo = new ToDo(this.taskDescription);
        taskList.addTask(todo);
        storage.saveToFile(taskList);
        return new UserCommand("\tGot it. I've added this task: ",
                "\t" + todo, taskList.getTaskSummary());
    }
}
