package luke.task;
import java.time.LocalDateTime;

/**
 * A task that takes place over a time period, from startDate to endDate.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of Event.
     * Uses the constructor of Task.
     * @param description The description of the task.
     * @param startDate The date that the task starts.
     * @param endDate The date that the task ends.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        assert startDate != null;
        assert endDate != null;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        String month = String.valueOf(startDate.getMonth());
        String monthInShortForm = month.charAt(0) + month.substring(1, 3).toLowerCase();
        String day = startDate.getDayOfMonth() < 10
                ? "0" + startDate.getDayOfMonth()
                : String.valueOf(startDate.getDayOfMonth());
        String minute = startDate.getMinute() < 10
                ? "0" + startDate.getMinute()
                : String.valueOf(startDate.getMinute());
        String hour = startDate.getHour() < 10
                ? "0" + startDate.getHour()
                : String.valueOf(startDate.getHour());
        String eMonth = String.valueOf(endDate.getMonth());
        String eMonthInShortForm = eMonth.charAt(0) + eMonth.substring(1, 3).toLowerCase();
        String eDay = endDate.getDayOfMonth() < 10
                ? "0" + endDate.getDayOfMonth()
                : String.valueOf(endDate.getDayOfMonth());
        String eMinute = endDate.getMinute() < 10
                ? "0" + endDate.getMinute()
                : String.valueOf(endDate.getMinute());
        String eHour = endDate.getHour() < 10
                ? "0" + endDate.getHour()
                : String.valueOf(endDate.getHour());
        return "[E]" + super.toString() + " (from: " + day + " " + monthInShortForm
                + " " + startDate.getYear() + " " + hour + ":" + minute + " to: "
                + eDay + " " + eMonthInShortForm + " " + endDate.getYear() + " " + eHour + ":"
                + eMinute + ")";
    }
}
