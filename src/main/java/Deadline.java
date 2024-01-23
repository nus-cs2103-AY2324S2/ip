public class Deadline extends Task {

    protected String endTime;

    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = endTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString().substring(3) + " (by: " + endTime + ")";
    }
}
