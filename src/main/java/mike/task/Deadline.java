package mike.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

/**
 * A Deadline task.
 * @author ningc
 */
public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    private final LocalDate deadline;

    /**
     * Constructor.
     * @param description The deadline task.
     * @param deadline When the task is due.
     * @throws MikeException If deadline String cannot be parsed as a {@link LocalDate} object.
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
        return super.inListView(listView) && listView.dateFilter(deadline) && listView.keywordFilter(description);
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
