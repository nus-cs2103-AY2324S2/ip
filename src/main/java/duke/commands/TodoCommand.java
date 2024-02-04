package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidArgumentException;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand extends Command {
    /**
     * The command word to add a todo task.
     */
    public static final String COMMAND_WORD = "todo";
    private static final String MESSAGE = "added this task for you liao:\n%s";

    private final String name;

    /**
     * Constructs a command to add a todo task with the given name.
     *
     * @param name The name of the todo task.
     */
    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        try {
            Task task = new TodoTask(name);
            tasks.add(task);
            ui.print(String.format(MESSAGE, task));
        } catch (InvalidArgumentException e) {
            ui.print(e.getMessage());
        }
    }
}
