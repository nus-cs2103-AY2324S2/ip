package luna.command;

import java.time.LocalDate;

import luna.Storage;
import luna.TaskList;
import luna.Ui;
import luna.entry.ListEntryDeadline;

/**
 * Represents a deadline command which to add a deadline task to the task list
 */
public class DeadlineCommand extends Command {

    private String taskName;
    private LocalDate endDate;

    /**
     * Construct a Command to have deadline command type and assign its task name and end date
     *
     * @param tn task name
     * @param ed end date
     */
    public DeadlineCommand(String tn, LocalDate ed) {
        super(CommandType.DEADLINE);
        taskName = tn;
        this.endDate = ed;
    }

    /**
     * Attach the reference task list, ui and storage to execute the command on.
     * Adds a new deadline task to the task list
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(new ListEntryDeadline(taskName, false, endDate));
        ui.shiftedPrint("Added Deadline task");
    }

}
