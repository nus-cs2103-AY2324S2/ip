package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;
import atlas.task.ToDo;


/**
 * This command adds a new event to the task list.
 */

public class AddToDoCommand extends Command {
    private String description;
    private int priority;

    /**
     * Constructs an {@code AddToDoCommand} with the specified description.
     *
     * @param tasks       The {@code TaskList} to which the deadline will be added.
     * @param ui          The {@code Ui} instance for user interaction.
     * @param storage     The {@code Storage} instance for saving the updated task list.
     * @param description The description of the ToDo.
     */
    public AddToDoCommand(TaskList tasks, Ui ui, Storage storage, String description, int priority) {
        super(tasks, ui, storage);
        this.description = description;
        this.priority = priority;
    }

    /**
     * Executes the addition of a new ToDo task.
     */
    @Override
    public String execute() {
        ToDo todo = new ToDo(description, priority);
        tasks.addTask(todo);
        return ui.showTaskAdded(tasks);
    }
}
