package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandDeadline extends Command {
    private Deadline deadline;

    public CommandDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                this.deadline, tasks.size()));
    }
}
