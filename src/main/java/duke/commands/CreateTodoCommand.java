package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Represents a command to create an todo task.
 */
public class CreateTodoCommand extends Command {
    private String description;

    /**
     * Constructs a new CreateTodoCommand object with the given description.
     *
     * @param description The description of the todo task.
     */
    public CreateTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        Task newTask = null;
        newTask = new ToDo(description);
        tasks.addTask(newTask);
        return newTask.displayTask(tasks.size());
    }
}
