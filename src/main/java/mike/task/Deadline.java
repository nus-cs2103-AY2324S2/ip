package mike.task;

import mike.ListView;
import mike.ListViewType;
import mike.MikeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private static final String TYPE = "Deadline";
    protected LocalDate deadline;

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
    public boolean in(ListView listView) {
        return super.in(listView) && listView.dateFilter(deadline);
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
