package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;

public class CommandDeadline extends Command {
    private Deadline deadline;

    public CommandDeadline(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(this.deadline);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                this.deadline, tasks.size()));
    }
}
