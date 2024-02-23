package duke.commands;

import static duke.constants.Constant.DATE_FORMATTER_FOR_PRINT;

import java.time.LocalDate;

import duke.tasks.TaskList;
import duke.ui.Ui;



/**
 * Represents a command to view schedules
 */
public class ViewSchedulesCommand extends Command {
    private LocalDate checkDate;

    public ViewSchedulesCommand(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        TaskList results = tasks.find(this.checkDate);
        if (results.size() != 0) {
            return "Here are the tasks for " + this.checkDate.format(DATE_FORMATTER_FOR_PRINT) + ":\n"
                    + results.displayTasks();
        }
        return "There is no tasks for this date.";
    }
}
