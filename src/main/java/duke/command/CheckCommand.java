package duke.command;

import duke.common.TaskList;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a check command initited by the user. <code>CheckCommand</code> would check for tasks due on a specific
 * date
 */
public class CheckCommand extends Command {
    public static final String COMMAND_WORD = "check";
    public static final String COMMAND_USAGE = "check: it checks whether there are any task due to a specific date.\n" +
            "Example: check 2022-01-01";
    private LocalDate checkDate;

    /**
     * Constructor of the CheckCommand
     * @param checkDate the date that the user wants to check
     */
    public CheckCommand(LocalDate checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * Checks the tasks due on a specific date and then display it to the user
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
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
        ui.showDueTaskList(dueTaskList, checkDate);
    }
}
