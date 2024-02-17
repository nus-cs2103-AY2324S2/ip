package cat.command;

import java.util.HashMap;

import cat.Storage;
import cat.TaskList;
import cat.task.Deadline;
import cat.task.Event;
import cat.task.Task;
import cat.task.Todo;
import cat.ui.Ui;
import cat.ui.response.Response;

/**
 * A command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Type type;
    private final HashMap<String, String> components;
    private final String description;

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
    public AddCommand(Type type, String description, HashMap<String, String> components) {
        assert description != null : "AddCommand description must not be null";
        assert components != null : "AddCommand Components must not be null";

        this.description = description;
        this.type = type;
        this.components = components;
    }

    @Override
    public Response execute(TaskList tasks, Storage storage) {
        try {
            Task task = createTask();
            tasks.addTask(task);
            return Ui.showAddedTask(task);
        } catch (Task.InvalidComponents e) {
            return Ui.showError(e);
        }
    }

    private Task createTask() throws Task.InvalidComponents {
        switch (type) {
        case Todo:
            return new Todo(description, components);
        case Deadline:
            return new Deadline(description, components);
        case Event:
            return new Event(description, components);
        default:
            throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
