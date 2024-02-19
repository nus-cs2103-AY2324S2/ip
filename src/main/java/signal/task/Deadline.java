package signal.task;

import java.time.LocalDate;
import java.time.LocalTime;

import static signal.Duke.formatDate;
import static signal.Duke.formatTime;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String description, String by) {
        super(description);
        String[] parseBy = by.split(" ");
        if (parseBy.length > 1) {
            this.byTime = LocalTime.parse(parseBy[1]);
        }
        this.byDate = LocalDate.parse(parseBy[0]);

//            this.by = by;
    }

    @Override
    public LocalDate getDue() {
        return this.byDate;
    }

    @Override
    public String toString() {
        return "D" + super.toString()
                + " | by: " + formatDate(byDate) + (byTime != null ? " " + formatTime(byTime) : "");
    }

    @Override
    public String checkType() {
        return "Deadline";
    }
}
