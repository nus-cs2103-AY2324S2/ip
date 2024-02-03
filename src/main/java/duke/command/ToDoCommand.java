package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;

/**
 * Command to create a new todo task.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructs a ToDo command.
     *
     * @param description Description of task.
     * */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command, creating a new todo, adding it to the list, saving to storage.
     * Also displays messages to user.
     *
     * @param list TaskList object containing current tasks.
     * @param ui To send instructions on how to update the user interface.
     * @param storage To update storage with new todo task.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task newTask = Task.createTask(TaskType.TODO, this.description, false);
        list.add(newTask);
        storage.save(list);
        ui.showMessage("added new ToDo: " + newTask);
        ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
    }
}