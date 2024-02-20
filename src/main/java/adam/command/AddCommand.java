package adam.command;

import adam.AdamException;
import adam.Storage;
import adam.task.Deadline;
import adam.task.Event;
import adam.task.Task;
import adam.task.TaskList;
import adam.task.Todo;
import adam.ui.Ui;

/**
 * {@inheritDoc}
 * Represents a command to add a task.
 */
public class AddCommand extends Command {
    private String command;
    private String desc;
    private String[] details;

    /**
     * Returns a command that contains the details of the task to add.
     *
     * @param details Task type, description, and optional date/times of task.
     */
    public AddCommand(String... details) {
        this.details = new String[details.length - 2];
        for (int i = 0; i < this.details.length; i++) {
            this.details[i] = details[i + 2];
        }
        this.command = details[0];
        this.desc = details[1];
    }

    /**
     * {@inheritDoc}
     * Adds the new task created to the list of tasks.
     *
     * @param taskList Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @return The result of the command executed to be printed as the program's response.
     * @throws AdamException If command cannot be executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        Task t;
        switch (command) {
        case "todo":
            assert details.length == 0: "todo should not have any details";
            t = new Todo(desc);
            taskList.add(t);
            break;
        case "deadline":
            assert details.length == 1: "deadline should have 1 detail";
            t = new Deadline(desc, details[0]);
            taskList.add(t);
            break;
        case "event":
            assert details.length == 2: "event should have 2 details";
            t = new Event(desc, details[0], details[1]);
            taskList.add(t);
            break;
        default:
            throw new AdamException("Unknown task to add");
        }
        return ui.showResult(
                "Got it. I've added this task:",
                t.toString(),
                "Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
