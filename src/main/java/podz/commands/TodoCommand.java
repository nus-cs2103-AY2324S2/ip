package podz.commands;

import podz.task.Todo;

/**
 * Represents a command to add a todo task in the task manager.
 */
public class TodoCommand extends Command {
    private Todo todo;

    /**
     * Constructs a TodoCommand object with the specified todo task.
     *
     * @param todo the todo task to be added.
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes the todo command to add a todo task.
     */
    @Override
    public String execute() {
        super.taskList.addTask(this.todo);
        super.response = "Sure thing! Added a new todo task:\n"
                        + this.todo + "\n"
                        + super.taskList.getListCounter();
        return super.response;
    }
}
