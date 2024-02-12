package tyler.command;

import tyler.task.TaskList;
import tyler.task.Event;
import tyler.ui.Ui;
import tyler.storage.Storage;

import java.time.LocalDateTime;

public class EventCommand extends AddCommand {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public EventCommand(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newTask = new Event(this.taskName, this.start, this.end);
        tasks.addTask(newTask);
        int num = tasks.getNumOfTasks();
        ui.showTaskAdded(newTask, num);
    }
}
