package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * @inheritDoc
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
     * Adds the new task created to the list of tasks.
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        switch (this.command) {
        case "todo":
            t = new Todo(this.desc);
            tasks.add(t);
            break;
        case "deadline":
            t = new Deadline(this.desc, this.details[0]);
            tasks.add(t);
            break;
        case "event":
            t = new Event(this.desc, this.details[0], this.details[1]);
            tasks.add(t);
            break;
        }
        ui.showResult("Got it. I've added this duke.task:");
        ui.showResult(t.toString());
        ui.showResult("Now you have " + tasks.size() + " task(s) in the list.");

    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
