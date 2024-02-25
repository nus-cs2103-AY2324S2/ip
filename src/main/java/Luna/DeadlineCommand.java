package Luna;

import java.time.LocalDate;

/**
 * Represents a deadline command which to add a deadline task to the task list
 */
public class DeadlineCommand extends Command {

    String taskName;
    LocalDate endDate;

    public DeadlineCommand(String tn, LocalDate ed) {
        super(CommandType.DEADLINE);
        taskName = tn;
        this.endDate = ed;
    }

    /**
     * Attach the reference tasklist, ui and storage to execute the command on.
     * Adds a new deadline task to the tasklist
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(new ListEntryDeadline(taskName, false, endDate));
    }

}
