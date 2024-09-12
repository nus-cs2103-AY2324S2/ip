package luna.entry;

import java.time.LocalDate;

/**
 * Represent a list entry for a event task.
 */
public class ListEntryEvent extends ListEntry {
    public ListEntryEvent(String task, boolean check, LocalDate tStart, LocalDate tEnd) {
        super(task, check, TYPE_EVENT, tStart, tEnd);
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.taskStart + " to: " + this.taskEnd + ")";
    }

    @Override
    public void snoozeEntry(int days) {
        this.taskEnd = taskEnd.plusDays(days);
    }

    @Override
    public boolean isSnoozable() {
        return true;
    }
}
