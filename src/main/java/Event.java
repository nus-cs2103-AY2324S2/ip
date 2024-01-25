public class Event implements Task{
    private final String name;
    private final boolean done;
    private final String startDate;
    private final String endDate;

    Event(String name, String startDate, String endDate) {
        this.name = name;
        this.done = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Event(String name, boolean done, String startDate, String endDate) {
        this.name = name;
        this.done = done;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event mark() {
        return new Event(this.name, true, this.startDate, this.endDate);
    }

    public Event unmark() {
        return new Event(this.name, false, this.startDate, this.endDate);
    }

    @Override
    public String toString() {
        return "[E][" + (this.done?"X":" ") + "] " + this.name +
            " (from: " + this.startDate + " to: " + this.endDate + ")";
    }
}
