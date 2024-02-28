package luna.command;

import luna.Storage;
import luna.TaskList;
import luna.Ui;

/**
 * Represents a snooze command which snooze a task from the task list
 */
public class SnoozeCommand extends Command {
    private final int index;
    private final int days;

    /**
     * Construct a Command to have snooze command type and assign its task name and end date
     *
     * @param index index of the list
     * @param days the number of days to delay
     */
    public SnoozeCommand(int index, int days) {
        super(CommandType.SNOOZE);
        this.index = index;
        this.days = days;
    }

    /**
     * Snooze an entry by a given number of days
     *
     * @param tl the task list
     * @param ui the program ui
     * @param storage list file storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (!tl.isValidIndex(index)) {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        } else if (!tl.isEntrySnoozable(index)) {
            new InvalidCommand("Specified task cannot be snoozed").execute(tl, ui, storage);

        } else {
            tl.snoozeEntry(index, days);
            ui.shiftedPrint("Snoozed " + tl.getEntry(index).getTaskName() + " by " + days + " days.");
        }
    }
}
