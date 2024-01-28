public class Deadline extends Task{
    private String due;

    Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due + ")";
    }
}
