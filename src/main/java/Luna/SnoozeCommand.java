package Luna;

/**
 * Represents a snooze command which snooze a task from the task list
 */
public class SnoozeCommand extends Command{
    int index;

    int days;

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
            ui.shiftedPrint("Snoozed " + tl.getEntry(index).task + " by " + days + " days.");
        }
    }
}
