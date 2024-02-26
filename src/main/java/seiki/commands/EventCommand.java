package seiki.commands;

import static seiki.common.DateTime.DATE_TIME_FORMAT;

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
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_FORMAT = COMMAND_WORD
            + " [TASK_TITLE] /from [START_DATE_TIME] /to [END_DATE_TIME]";
    public static final String COMMAND_HELPER = "Please use the following format: " + COMMAND_FORMAT;
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Creates an event task.\n"
            + "Parameters: TASK_TITLE, START_DATE_TIME (must be " + DATE_TIME_FORMAT
            + "), END_DATE_TIME (must be " + DATE_TIME_FORMAT + ")\n"
            + "Example: " + COMMAND_WORD + " read book /from 2022/02/22 1234 /to 2022/02/22 2234";
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
        assert !taskTitle.isEmpty() : "Task name should not be empty";
        assert startDateTime != null : "Start date and time should not be empty";
        assert endDateTime != null : "End date and time should not be empty";

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
