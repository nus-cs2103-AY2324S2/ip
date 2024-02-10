package duke.command;

import java.util.HashMap;

import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * A command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Type type;
    private final HashMap<String, String> components;

    /**
     * The possible types of tasks.
     */
    public enum Type {
        Todo,
        Deadline,
        Event,
    }

    /**
     * Constructs a command that adds a task to the task list.
     *
     * @param type       the type of the task
     * @param components the components parsed from the user command
     */
    public AddCommand(Type type, HashMap<String, String> components) {
        assert components != null : "Components must not be null";

        this.type = type;
        this.components = components;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            tasks.addTask(task);
            ui.showAddedTask(task);
        } catch (Task.InvalidComponents e) {
            ui.showError(e);
        }
    }

    private Task createTask() throws Task.InvalidComponents {
        switch (type) {
        case Todo:
            return new Todo(components);
        case Deadline:
            return new Deadline(components);
        case Event:
            return new Event(components);
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
