package seiki.commands;

import java.time.LocalDateTime;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.EventTask;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'event' command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_HELPER = "Please use the following format: "
            + "event [task title] /from [startdatetime] /to [enddatetime]";
    public static final String COMMAND_WORD = "event";
    private final String taskTitle;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructor for EventCommand.
     * @param taskTitle     task title of Event
     * @param startDateTime start date & time of Event
     * @param endDateTime   end date & time of Event
     */
    public EventCommand(String taskTitle, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.taskTitle = taskTitle;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        EventTask task = new EventTask(taskTitle, startDateTime, endDateTime);
        taskList.addTask(task);
        storage.save(taskList);
        return ui.showAddTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
