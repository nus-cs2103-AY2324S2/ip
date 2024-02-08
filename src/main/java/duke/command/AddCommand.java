package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private final Task task;

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
