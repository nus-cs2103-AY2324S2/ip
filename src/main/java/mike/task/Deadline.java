package mike.task;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Deadline task.
 * @author ningc
 */
public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected LocalDate deadline;

    /**
     * Construcotr.
     * @param description The deadline task.
     * @param deadline When the task is due.
     * @throws MikeException todo
     */
    public Deadline(String description, String deadline) throws MikeException {
        super(description, TYPE);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new MikeException("Please enter a valid date in YYYY-MM-DD format.");
        }
        tags.add(ListViewType.DATE);
    }

    @Override
    public boolean inListView(ListView listView) {
        return super.inListView(listView) &&
                (!listView.getType().equals(ListViewType.DATE) || listView.dateFilter(deadline));
    }

    private String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String getFileEncoding() {
        return super.getFileEncoding() + "," + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
