package toothless.commands;

import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;
import toothless.tasks.Event;

/**
 * Represents a command to add an event task to the task list.
 * The detail provided includes the event description and its start and end dates.
 */
public class EventCommand extends Command{
    private String detail;

    /**
     * Constructs an EventCommand with the specified detail, which includes the task description
     * and its start and end dates.
     * @param detail The task detail, expected to contain a description, a start date, and an end date.
     */
    public EventCommand(String detail) {
        this.detail = detail;
    }

    /**
     * Executes the command to add a new event task to the task list. Parses the task detail to separate
     * the description from the start and end dates, creates a new event task, adds it to the task list,
     * and displays a message to the user about the addition.
     * @param ui The user interface to interact with.
     * @param taskList The task list where the new task will be added.
     * @param storage The storage system, not directly used by this command.
     * @return String message to be displayed to the user.
     * @throws ToothlessException If the task detail is invalid,
     *  either missing a description, a start date, or an end date.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (detail.equals("")) {
            throw new ToothlessException(ui.showNoTaskNameWarning());
        }

        int date1Index = detail.indexOf("/from");
        if (date1Index == -1) {
            throw new ToothlessException(ui.showEventNoStartWarning());
        }
        String description = detail.substring(0, date1Index - 1);
        detail = detail.substring(date1Index + 6);

        int date2Index = detail.indexOf("/to");
        if (date2Index == -1) {
            throw new ToothlessException(ui.showEventNoEndWarning());
        }

        String startDate = detail.substring(0, date2Index - 1);
        String endDate = detail.substring(date2Index + 4);

        Task t = new Event(description, startDate, endDate);
        taskList.addTask(t);

        return ui.showAddedTask(t, taskList.size());
    }

    /**
     * Indicates whether the command is an exit command.
     * @return False, as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
