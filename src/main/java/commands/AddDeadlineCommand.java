package commands;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Deadline;

import java.time.LocalDateTime;

/**
 * Add Deadline to task list command.
 */
public class AddDeadlineCommand extends AbstractCommand {
    private String name;
    private LocalDateTime by;
    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        Deadline deadline = new Deadline(this.name, this.by);
        taskList.addTask(deadline);
        storage.saveTasks(taskList);
        return new UserCommand("\tAdded deadline: ", "\t" + deadline, taskList.getTaskSummary());
    }
}
