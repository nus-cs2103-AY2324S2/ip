package podz.commands;

import podz.task.Todo;
import podz.ui.Ui;

/**
 * Represents a command to add a todo task in the task manager.
 */
public class TodoCommand extends Command {
    private Todo todo;

    /**
     * Constructs a TodoCommand object with the specified todo task.
     *
     * @param todo the todo task to be added
     */
    public TodoCommand(Todo todo) {
        this.todo = todo;
    }

    /**
     * Executes the todo command to add a todo task.
     *
     * @param ui the user interface that interacts with the user
     */
    @Override
    public void execute(Ui ui) {
        super.taskList.addTask(this.todo);
        ui.printToUser("\tGot it. I've added this task:",
                        "\t" + this.todo,
                        super.taskList.getListCounter());
    }
}