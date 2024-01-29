public class Event extends Task {
    private static final String TASK_TYPE = "E";
    private String from;
    private String to;

    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s,%s", TASK_TYPE, this.getIsMarked() ? "T" : "F", this.getTitle(), this.from, this.to);
    }

    
    @Override
    public String toString() {
        return String.format("[%s]%s (From: %s To: %s)", TASK_TYPE, super.toString(), this.from, this.to);
    }
    
}
