package commands;

import tasks.Task;

/**
 *  Extends {@link AddCommand} and specifies the adding of todo task
 */
public class AddTodoCommand extends AddCommand {
    public AddTodoCommand(Task task) {
        super(task);
    }

    public AddTodoCommand() {
        super(null);
    }

    @Override
    public String showUsage() {
        return "Usage: Todo {task description};";
    }
}
