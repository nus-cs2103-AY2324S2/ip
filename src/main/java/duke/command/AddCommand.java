package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.HashMap;

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
     * @param type the type of the task
     * @param components the components parsed from the user command
     */
    public AddCommand(Type type, HashMap<String, String> components) {
        this.type = type;
        this.components = components;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task;
            switch (type) {
            case Todo:
                task = new Todo(components);
                break;
            case Deadline:
                task = new Deadline(components);
                break;
            case Event:
                task = new Event(components);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
            }
            tasks.addTask(task);
            ui.showAddedTask(task);
        } catch (Task.InvalidComponents e) {
            ui.showError(e);
        }
    }
}
