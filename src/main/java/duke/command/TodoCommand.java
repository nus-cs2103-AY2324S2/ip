package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {

    private String description;

    /**
     * Constructs a TodoCommand object with the specified input and description.
     *
     * @param input       The input command string.
     * @param description The description of the todo task.
     */
    public TodoCommand(String input, String description) {
        super(input);
        this.description = description;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t = new Todo(description);
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
