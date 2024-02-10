package commands;

import java.time.LocalDateTime;

import exceptions.DukeException;
import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Deadline;


/**
 * Add Deadline to task list command.
 */
public class AddDeadlineCommand extends AbstractCommand {
    private String name;
    private LocalDateTime by;
    /**
     * AddDeadlineCommand constructor.
     */
    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        Deadline deadline = new Deadline(this.name, this.by);
        try {
            taskList.addTask(deadline);
        } catch (DukeException e) {
            return new UserCommand("\tA deadline with the same name / time already exists.");
        }
        storage.saveTasks(taskList);
        return new UserCommand("\tAdded deadline: ", "\t" + deadline, taskList.getTaskSummary());
    }
}
