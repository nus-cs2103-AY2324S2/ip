import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDateTime fromDateTime;

    protected LocalDate toDate;
    protected LocalDateTime toDateTime;

    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    public Event(String description, LocalDateTime fromDateTime, LocalDate toDate) {
        super(description);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    public Event(String description, LocalDate fromDate, LocalDateTime toDateTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    public Event(String description, boolean isDone, LocalDate fromDate, LocalDate toDate) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    public Event(String description, boolean isDone, LocalDateTime fromDateTime, LocalDate toDate) {
        super(description, isDone);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = toDate;
        this.toDateTime = null;
    }

    public Event(String description, boolean isDone, LocalDate fromDate, LocalDateTime toDateTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromDateTime = null;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    public Event(String description, boolean isDone, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description, isDone);
        this.fromDate = null;
        this.fromDateTime = fromDateTime;
        this.toDate = null;
        this.toDateTime = toDateTime;
    }

    private String padZero(int s) {
        if (s < 10) {
            return "0" + s;
        }
        return Integer.toString(s);
    }

    public String getEventFromForSave() {
        return this.fromDate == null
                ? (this.fromDateTime.getYear() + "-" + padZero(this.fromDateTime.getMonthValue())
                + "-" + padZero(this.fromDateTime.getDayOfMonth()) + " " + padZero(this.fromDateTime.getHour()) + ":"
                + padZero(this.fromDateTime.getMinute()))
                : (this.fromDate.getYear() + "-" + padZero(this.fromDate.getMonthValue())
                + "-" + padZero(this.fromDate.getDayOfMonth()));
    }

    public String getEventToForSave() {
        return this.toDate == null
                ? (this.toDateTime.getYear() + "-" + padZero(this.toDateTime.getMonthValue())
                + "-" + padZero(this.toDateTime.getDayOfMonth()) + " " + padZero(this.toDateTime.getHour()) + ":"
                + padZero(this.toDateTime.getMinute()))
                : (this.toDate.getYear() + "-" + padZero(this.toDate.getMonthValue())
                + "-" + padZero(this.toDate.getDayOfMonth()));
    }

    public String getEventFromForDisplay() {
        return this.fromDate == null
                ? (this.fromDateTime.getMonth() + " " + padZero(this.fromDateTime.getDayOfMonth())
                + " " + this.fromDateTime.getYear() + " " + padZero((this.fromDateTime.getHour() > 12
                ? this.fromDateTime.getHour() - 12 : this.fromDateTime.getHour())) + ":"
                + padZero(this.fromDateTime.getMinute()) + (this.fromDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.fromDate.getMonth() + " " + padZero(this.fromDate.getDayOfMonth())
                + " " + this.fromDate.getYear());
    }

    public String getEventToForDisplay() {
        return this.toDate == null
                ? (this.toDateTime.getMonth() + " " + padZero(this.toDateTime.getDayOfMonth())
                + " " + this.toDateTime.getYear() + " " + padZero((this.toDateTime.getHour() > 12
                ? this.toDateTime.getHour() - 12 : this.toDateTime.getHour())) + ":"
                + padZero(this.toDateTime.getMinute()) + (this.toDateTime.getHour() >= 12 ? "PM" : "AM"))
                : (this.toDate.getMonth() + " " + padZero(this.toDate.getDayOfMonth())
                + " " + this.toDate.getYear());
    }

    @Override
    public String getDescriptionStatus() {
        return "[E]" + (isDone ? "[X] " : "[ ] ") + this.description + " (from: " + this.getEventFromForDisplay()
                + " to: " + this.getEventToForDisplay() + ")";
    }

    @Override
    public String[] getFields() {
        String[] result = new String[4];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        result[2] = this.getEventFromForSave();
        result[3] = this.getEventToForSave();
        return result;
    }
}
