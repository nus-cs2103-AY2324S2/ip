package seiki.commands;

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
    public static final String COMMAND_HELPER = "Please use the following format: deadline [task title] /by [datetime]";
    public static final String COMMAND_WORD = "deadline";
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
