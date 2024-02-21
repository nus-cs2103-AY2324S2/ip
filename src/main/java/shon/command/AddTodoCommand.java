package shon.command;

import shon.TaskList;

/**
 * Represents a command to add a <code>Todo</code> task.
 */
public class AddTodoCommand extends AddTaskCommand {
    /**
     * Creates a new command to add a <code>Todo</code> task.
     *
     * @param description The description of the <code>Todo</code> task to be added.
     */
    public AddTodoCommand(String description) {
        super(description);
    }

    /**
     * Adds the <code>Todo</code> task to the list tasks, and outputs the result of the command.
     *
     * @param tasks The <code>TaskList</code> to add the <code>Deadline</code> task to.
     */
    @Override
    public String execute(TaskList tasks) {
        return String.join("\n", tasks.addTodo(this.description));
    }
}
