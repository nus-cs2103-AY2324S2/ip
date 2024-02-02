package kitchensink.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String month = String.valueOf(dueDate.getMonth());
        String shortForm = month.charAt(0) + month.substring(1, 3).toLowerCase();
        String day = dueDate.getDayOfMonth() < 10 ? "0" + dueDate.getDayOfMonth()
                : String.valueOf(dueDate.getDayOfMonth());
        String minute = dueDate.getMinute() < 10 ? "0" + dueDate.getMinute()
                : String.valueOf(dueDate.getMinute());
        String hour = dueDate.getHour() < 10 ? "0" + dueDate.getHour()
                : String.valueOf(dueDate.getHour());
        return "[D]" + super.toString() + " (by: " + day + " " + shortForm
                + " " + dueDate.getYear() + " " + hour + ":" + minute + ")";
    }
}
