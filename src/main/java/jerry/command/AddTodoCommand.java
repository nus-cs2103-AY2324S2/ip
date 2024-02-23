package jerry.command;

import jerry.TaskList;
import jerry.ToDo;
import jerry.Ui;

/**
 * Represents a command to add a new ToDo task to the task list.
 * <p>
 * This command allows users to add simple tasks without a specific deadline or time frame,
 * identified by a description provided at creation.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddTodoCommand} with the specified UI, task list, and task description.
     *
     * @param ui          The UI component for interacting with the user.
     * @param tasks       The task list to which the new task will be added.
     * @param description The description of the ToDo task.
     */
    public AddTodoCommand(Ui ui, TaskList tasks, String description) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.description = description;
    }

    /**
     * Executes the addition of a ToDo task to the task list.
     * <p>
     * Upon execution, a new ToDo task with the provided description is created and added to the task list.
     * The user is then notified of the successful addition of the task.
     */
    @Override
    public String execute() {
        try {
            if (description.trim().isEmpty()) {
                throw new CommandFormatException("Error: Wrong format \nUsage: todo <task description>");
            }
            ToDo todo = new ToDo(description);
            tasks.addTask(todo);
            return ui.showAdded(todo, tasks);
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
