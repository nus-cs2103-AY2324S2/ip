import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        super.isDone = false;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String month = String.valueOf(dueDate.getMonth());
        String shortForm = month.charAt(0) + month.substring(1, 3).toLowerCase();
        String minute = dueDate.getMinute() < 10 ? "0" + dueDate.getMinute()
                : String.valueOf(dueDate.getMinute());
        String hour = dueDate.getHour() < 10 ? "0" + dueDate.getHour()
                : String.valueOf(dueDate.getHour());
        return "[D]" + super.toString() + " (by: " + dueDate.getDayOfMonth() + " " + shortForm
                + " " + dueDate.getYear() + " " + hour + ":" + minute + ")";
    }
}
