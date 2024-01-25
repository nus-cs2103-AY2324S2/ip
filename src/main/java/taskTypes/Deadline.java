package taskTypes;

public class Deadline extends Task {
    private String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }
    public String statusString() {
        return String.format("[D]%s (by: %s)", super.statusString(), this.date);
    }
}
