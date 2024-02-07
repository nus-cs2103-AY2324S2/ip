public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        String dueSplit[] = due.split(" ", 2);
        return "[D]" + super.toString() + "(" + dueSplit[0] + ": " + dueSplit[1] + ")";
    }
}
