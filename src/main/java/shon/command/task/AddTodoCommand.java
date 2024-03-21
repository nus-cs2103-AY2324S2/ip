package shon.command.task;

import shon.task.TaskList;

/**
 * Represents a command to add a <code>Todo</code> task.
 */
public class AddTodoCommand extends AddTaskCommand {
    /**
     * Creates a new command to add a <code>Todo</code> task.
     *
     * @param tasks The TaskList associated with this command.
     * @param description The description of the <code>Todo</code> task to be added.
     */
    public AddTodoCommand(TaskList tasks, String description) {
        super(tasks, description);
    }

    /**
     * Adds the <code>Todo</code> task to the list tasks, and outputs the result of the command.
     *
     */
    @Override
    public String execute() {
        return String.join("\n", this.tasks.addTodo(this.description));
    }
}
