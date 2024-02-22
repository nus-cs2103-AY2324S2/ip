package command;

import sky.Ui;
import task.TaskList;
import task.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructor for AddTodoCommand.
     * @param description Description of the todo.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a todo task to the task list and shows the user the added task.
     * @param tasks Task list to add the todo task to.
     * @param ui Ui to display the added task to the user.
     * @return String to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Todo todo = new Todo(description);
        tasks.add(todo);
        return ui.showAddTask(todo, tasks.size());
    }
}
