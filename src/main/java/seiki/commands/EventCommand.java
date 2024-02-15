package seiki.commands;

import static seiki.common.DateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.data.task.Event;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'event' command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final ArrayList<String> args;

    public EventCommand(ArrayList<String> args) {
        this.args = args;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        if (!this.args.contains("/from") && !this.args.contains("/to")) {
            throw new SeikiException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (this.args.contains("/from")
                && this.args.contains("/to")
                && (this.args.subList(0, this.args.indexOf("/from")).size() == 0
                && this.args.subList(this.args.indexOf("/to") + 1, this.args.size()).size() == 0
                && this.args.subList(this.args.indexOf("/from") + 1, this.args.indexOf("/to")).size() == 0)) {
            throw new SeikiException("The task title, start and end date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!this.args.contains("/to")
                || this.args.subList(this.args.indexOf("/to") + 1, this.args.size()).size() == 0) {
            throw new SeikiException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (!this.args.contains("/from")
                || this.args.subList(this.args.indexOf("/from") + 1, this.args.indexOf("/to")).size() == 0) {
            throw new SeikiException("The date time for the task is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else if (this.args.subList(0, this.args.indexOf("/from")).size() == 0) {
            throw new SeikiException("The task title is missing.\n"
                    + "Please use the following format: event [task title] /from [startdatetime] /to [enddatetime]");
        } else {
            String taskName = String.join(" ", this.args.subList(0, this.args.indexOf("/from")));
            String startDateTimeStr = String.join(" ",
                    this.args.subList(this.args.indexOf("/from") + 1, this.args.indexOf("/to")));
            String endDateTimeStr = String.join(" ",
                    this.args.subList(this.args.indexOf("/to") + 1, this.args.size()));
            try {
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr, DATE_TIME_FORMATTER);
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr, DATE_TIME_FORMATTER);
                Event task = new Event(taskName, startDateTime, endDateTime);
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
