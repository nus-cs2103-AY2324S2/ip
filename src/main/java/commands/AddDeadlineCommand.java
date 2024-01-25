package commands;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Deadline;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String name;
    private LocalDateTime by;
    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        Deadline deadline = new Deadline(this.name, this.by);
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
    }
}
