package lite.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * {@inheritDoc}
     *
     * Date is displayed in format: (from: Monday 2pm to: Tuesday 4pm)
     */
    @Override
    public String toString() {
        String startSplit[] = start.split(" ", 2);
        String endSplit[] = end.split(" ", 2);
        return "[E]" + super.toString() + " (" + startSplit[0] + ": " + startSplit[1] + " " +
                endSplit[0] + ": " + endSplit[1] + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveToFile() {
        return "E!" + super.saveToFile() + "!" + start + "!" + end + "\n";
    }
}
