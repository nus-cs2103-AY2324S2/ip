public class Event extends Task {
    private String start;
    private String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String printTask() {
        return "[E]" + super.printTask() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + " | " + this.start + " | " + this.end;
    }
}
