package anxi.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import anxi.command.AnxiException;
import anxi.command.TaskList;
import anxi.command.Ui;

/**
 * Handles commands related to viewing of tasks on specific date.
 */
public class ViewHandler extends Handler {
    /**
     * ViewHandler constructor.
     */
    public ViewHandler() {
    }

    /**
     * Finds and returns a string of tasks on specific date.
     * @param date          String input of date.
     * @param taskList      Instance of TaskList
     * @param ui            Instance of Ui
     * @return tasks        String of all tasks that fall on specific date.
     */
    public String viewSchedule(String date, TaskList taskList, Ui ui) {
        try {
            return viewOnDate(date, taskList, ui);
        } catch (AnxiException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to find tasks that fall on a specific date.
     * @param date          String input of date.
     * @param taskList      Instance of TaskList
     * @param ui            Instance of Ui
     * @return tasks        String of all tasks that fall on specific date.
     * @throws AnxiException    Thrown if there is an invalid date.
     */
    private String viewOnDate(String date, TaskList taskList, Ui ui) throws AnxiException {
        if (date.isBlank()) {
            throw new AnxiException("No date honey, try again.");
        }

        LocalDate find = parseDate(date);

        String events = taskList.findAllEventOnDate(find);
        String deadlines = taskList.findAllDeadlineOnDate(find);

        if (events.isBlank() && deadlines.isBlank()) {
            return "Nothing on " + find.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }

        return ui.printTasksOnDay(find.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                events, deadlines);
    }
}
