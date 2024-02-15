package seiki.commands;

import static seiki.common.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
    private final ArrayList<String> args;
    public DeadlineCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        StringBuilder sb = new StringBuilder();
        if (!this.args.contains("/by")
                || this.args.subList(this.args.indexOf("/by") + 1, this.args.size()).size() == 0) {
            throw new SeikiException("The date time for the task is not found.\n"
                    + "Please use the following format: deadline [task title] /by [datetime]");
        } else if (this.args.subList(0, this.args.indexOf("/by")).size() == 0) {
            throw new SeikiException("The task title is missing.\n"
                    + "Please use the following format: deadline [task title] /by [datetime]");
        } else {
            String taskName = String.join(" ", this.args.subList(0, this.args.indexOf("/by")));
            String dateTimeStr = String.join(" ",
                    this.args.subList(this.args.indexOf("/by") + 1, this.args.size()));
            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
                DeadlineTask task = new DeadlineTask(taskName, dateTime);
                taskList.addTask(task);
                storage.save(taskList);

                return ui.showAddTask(task, taskList);
            } catch (DateTimeParseException e) {
                throw new SeikiException("The format of the date time is incorrect.\n"
                        + "Please use the following format: yyyy/MM/dd HHmm");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
