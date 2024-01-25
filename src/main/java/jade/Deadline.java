package jade;

public class Deadline extends Task{
    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),this.deadlineDate);
    }
}
