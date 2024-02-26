package seiki.commands;

import static seiki.common.DateTime.DATE_TIME_FORMAT;

import java.time.LocalDateTime;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.DeadlineTask;
import seiki.storage.Storage;
import seiki.ui.Ui;


/**
 * Represents the 'deadline' command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " [TASK_TITLE] /by [DATETIME]";
    public static final String COMMAND_HELPER = "Please use the following format: " + COMMAND_FORMAT;
    public static final String COMMAND_USAGE = COMMAND_WORD
            + ": Creates a deadline task.\n"
            + "Parameters: TASK_TITLE, DATETIME (must be " + DATE_TIME_FORMAT + ")\n"
            + "Example: " + COMMAND_WORD + " read book /by 2022/02/22 1234";
    private final String taskTitle;
    private final LocalDateTime dateTime;

    /**
     * Constructor for DeadlineCommand
     * @param taskTitle task title of Deadline
     * @param dateTime  date & time of when Deadline is due by
     */
    public DeadlineCommand(String taskTitle, LocalDateTime dateTime) {
        this.taskTitle = taskTitle;
        this.dateTime = dateTime;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        assert !taskTitle.isEmpty() : "Task name should not be empty";
        assert dateTime != null : "Date and time should not be empty";

        DeadlineTask task = new DeadlineTask(taskTitle, dateTime);
        taskList.addTask(task);
        storage.save(taskList);
        return ui.showAddTask(task, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
