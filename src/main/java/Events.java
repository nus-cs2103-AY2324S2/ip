public class Events extends Task{

    protected String start;
    protected String end;

    public Events(String task, String start, String end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        return "E | " + done + " | " + super.toString() + " | "
                + start + " | " + end +"\n";
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
