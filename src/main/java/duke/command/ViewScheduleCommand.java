package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the Command of viewing tasks of a certain day.
 */
public class ViewScheduleCommand extends Command {
    private LocalDate date;

    /**
     * Initializes a Command to view schedule that is on the date field.
     *
     * @param type the type of the Command which is find.
     * @param date the date for viewing the tasks.
     */
    public ViewScheduleCommand(Parser.Cmd type, LocalDate date) {
        super(type);
        this.date = date;
    }

    /**
     * Displays deadline ending on the date and event starting on the date.
     *
     * @param taskList the given taskList to mark/unmark the task.
     * @param ui
     */
    @Override
    public void run(TaskList taskList, Ui ui) {
        ArrayList<Task> tasks = taskList.searchDate(this.date);
        ui.displaySelectedList(tasks);
    }
}
