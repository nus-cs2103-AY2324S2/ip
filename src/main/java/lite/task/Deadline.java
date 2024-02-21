package lite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime due;
    private String timeDescription;

    public Deadline(String description, String due) {
        super(description);
        this.timeDescription = due;
        String time = due.split("by ")[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.due = LocalDateTime.parse(time, formatter);
    }

    /**
     * {@inheritDoc}
     *
     * Date is displayed in the format: Monday 2pm
     *
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due.getMonth() + " "
                + due.getDayOfMonth() + " " + due.getYear() + " "
                + due.getHour() + ":" + due.getMinute() + ")";

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveToFile() {
        return "D!" + super.saveToFile() + "!" + timeDescription + "\n";
    }
}
