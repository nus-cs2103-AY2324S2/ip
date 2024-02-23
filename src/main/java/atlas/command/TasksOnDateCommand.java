package atlas.command;

import atlas.Storage;
import atlas.TaskList;
import atlas.Ui;

import java.time.LocalDate;

/**
 * This command handles listing the tasks that fall on a specific date.
 */
public class TasksOnDateCommand extends Command {
    private LocalDate date;


    /**
     * Constructs a TasksOnDateCommand with the specified TaskList, Ui, Storage, and date.
     * This command is responsible for displaying all tasks scheduled for the given date.
     *
     * @param tasks   The TaskList containing the tasks.
     * @param ui      The Ui instance for user interaction.
     * @param storage The Storage instance, not directly used by this command.
     * @param date    The date for which to list the tasks.
     */
    public TasksOnDateCommand(TaskList tasks, Ui ui, Storage storage, LocalDate date) {
        super(tasks, ui, storage);
        this.date = date;
    }

    /**
     * Executes the listing of tasks on a specific date.
     * The tasks scheduled for the specified date are displayed to the user through the Ui.
     */
    @Override
    public String execute() {
        return ui.showTasksOnDate(tasks, date);
    }
}
