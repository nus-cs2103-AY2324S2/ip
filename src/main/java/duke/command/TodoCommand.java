package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.TreeMap;

public class TodoCommand extends Command {

    private String description;
    private String tag;
    private boolean isTagged;

    /**
     * Constructs a TodoCommand object with the specified input, description and tag.
     *
     * @param input       The input command string.
     * @param description The description of the todo task.
     * @param tag         The tag of the todo task.
     */
    public TodoCommand(String input, String description, String tag) {
        super(input);
        this.description = description;
        this.tag = tag;
        this.isTagged = true;
    }

    /**
     * Constructs a TodoCommand object with the specified input and description.
     *
     * @param input       The input command string.
     * @param description The description of the todo task.
     */
    public TodoCommand(String input, String description) {
        super(input);
        this.description = description;
        this.tag = "null";
        this.isTagged = false;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t;
        if (isTagged) {
            t = new Todo(description, tag);
        } else {
            t = new Todo(description);
        }
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
