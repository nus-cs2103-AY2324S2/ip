package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.HashMap;

public class AddCommand extends Command {
    private final Type type;
    private final HashMap<String, String> components;

    public enum Type {
        Todo,
        Deadline,
        Event,
    }

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
            ui.showAdd(task);
        } catch (Task.InvalidComponents e) {
            ui.showError(e);
        }
    }
}
