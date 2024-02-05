public class Deadline extends Task{

    private final String deadline_date;
    public Deadline(String description, String by) {
        super(description);
        this.deadline_date = by;
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline_date + ")";
    }

}
