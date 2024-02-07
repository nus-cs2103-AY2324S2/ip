package duke.command;

import duke.*;
import duke.task.*;

import java.time.LocalDateTime;


public class AddCommand extends Command {
    private Task task;
    public AddCommand(String desc) {
       task = new Todo(desc);
    }

    public AddCommand(String desc, LocalDateTime deadline) {
        task = new Deadline(desc, deadline);
    }

    public AddCommand(String desc, LocalDateTime from, LocalDateTime to) {
        task = new Event(desc, from, to);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks);
        ui.showAdded(task);
        ui.showTasksStatus(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
