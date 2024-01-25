package tasks;

public class Deadline extends Task {
    private String endDate;
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.endDate);
    }
}
