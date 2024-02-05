package commands;

import java.time.LocalDateTime;

import services.Storage;
import services.TaskList;
import services.UI;
import tasks.Event;


/**
 * Add Event to task list command.
 */
public class AddEventCommand extends AbstractCommand {
    private String name;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * AddEventCommand constructor.
     */
    public AddEventCommand(String name, LocalDateTime start, LocalDateTime end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        Event event = new Event(this.name, this.start, this.end);
        taskList.addTask(event);
        storage.saveTasks(taskList);
        return new UserCommand("\tAdded event: ", "\t" + event, taskList.getTaskSummary());
    }
}
