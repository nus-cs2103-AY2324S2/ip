package lite.task;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String startSplit[] = start.split(" ", 2);
        String endSplit[] = end.split(" ", 2);
        return "[E]" + super.toString() + " (" + startSplit[0] + ": " + startSplit[1] + " " +
                endSplit[0] + ": " + endSplit[1] + ")";
    }

    @Override
    public String saveToFile() {
        return "E!" + super.saveToFile() + "!" + start + "!" + end + "\n";
    }
}
