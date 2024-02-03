package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class CommandEvent extends Command {
    private Event event;

    public CommandEvent(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.event);

        storage.saveTasks(tasks);

        ui.showMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
            this.event, tasks.size()));
    }
}
