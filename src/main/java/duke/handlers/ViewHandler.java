package duke.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.DukeException;
import duke.command.TaskList;
import duke.command.Ui;

/**
 * Handles commands related to viewing of tasks on specific date.
 */
public class ViewHandler {
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
        } catch (DukeException de) {
            return ui.printErrorMessage(de.getErrorMessage());
        }
    }

    /**
     * Parses and calls relevant methods to find tasks that fall on a specific date.
     * @param date          String input of date.
     * @param taskList      Instance of TaskList
     * @param ui            Instance of Ui
     * @return tasks        String of all tasks that fall on specific date.
     * @throws DukeException    Thrown if there is an invalid date.
     */
    private String viewOnDate(String date, TaskList taskList, Ui ui) throws DukeException {
        if (date.isEmpty()) {
            throw new DukeException("No date honey, try again.");
        }

        TimeHandler th = new TimeHandler();
        LocalDate find = th.parseDate(date);

        String events = taskList.findAllEventOnDate(find);
        String deadlines = taskList.findAllDeadlineOnDate(find);

        if (events.isEmpty() && deadlines.isEmpty()) {
            throw new DukeException("Nothing on "
                    + find.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        }

        return ui.printTasksOnDay(find.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                events, deadlines);
    }
}
