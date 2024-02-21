package seedu.duke.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.common.TaskList;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.ui.Ui;


/**
 * Represents a check command initited by the user. <code>CheckCommand</code> would check for tasks due on a specific
 * date
 */
public class CheckCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String COMMAND_USAGE = "check: it checks whether there are any task due to a specific date.\n"
            + "Example: check 2022-01-01";
    private LocalDate checkDate;

    /**
     * Constructor of the CheckCommand
     *
     * @param checkDate the date that the user wants to check
     */
    public CheckCommand(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * Checks the tasks due on a specific date and then display it to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> dueTaskList = new ArrayList<>();


        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            if (!task.getHasDone()) {
                if (task instanceof Deadline && ((Deadline) task).getDeadline().toLocalDate().isEqual(this.checkDate)) {
                    dueTaskList.add(task);
                }
                if (task instanceof Event && ((Event) task).getEndDate().toLocalDate().isEqual(this.checkDate)) {
                    dueTaskList.add(task);
                }
            }
        }


        ui.generateDueTaskListResponse(dueTaskList, checkDate);
    }
}
