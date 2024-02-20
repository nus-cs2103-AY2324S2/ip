package mychats.command;

import mychats.main.Storage;
import mychats.main.TaskList;
import mychats.main.Ui;

import java.time.LocalDate;

/**
 * Represents a command to display tasks from the task list that happen on a given date.
 */
public class ViewCommand extends Command {

    private LocalDate targetDate;

    /**
     * Constructs a ViewCommand with the given target date.
     *
     * @param targetDate Target date for which the tasks from the task list will be displayed.
     */
    public ViewCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Executes the ViewCommand by displaying tasks from
     * the task list that happen on a given date using Ui.
     *
     * @param tasks TaskList that contains the task list.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage used to load and save tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasksOn(targetDate, tasks.getTasks());
    }
}
