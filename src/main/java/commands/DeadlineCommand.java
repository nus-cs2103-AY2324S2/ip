package commands;

import util.Ui;
import util.TaskList;
import util.Storage;
import tasks.Deadline;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String taskDescription;
    private LocalDateTime due;
    public DeadlineCommand(String taskDescription, LocalDateTime due) {
        this.taskDescription = taskDescription;
        this.due = due;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.taskDescription, this.due);
        taskList.addTask(deadline);
        storage.saveToFile(taskList);
    }
}
