package duke.command;

import duke.Storage;
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
     * Also updates response.
     *
     * @param list TaskList object containing current tasks.
     * @param storage To update storage with new todo task.
     */
    public void execute(TaskList list, Storage storage) {
        Task newTask = Task.createTask(TaskType.TODO, this.description, false);
        list.add(newTask);
        storage.save(list);

        super.setResponse("added new ToDo: " + newTask
                + "\nLooks like you have " + list.countTasks() + " things left to do!");
    }
}
