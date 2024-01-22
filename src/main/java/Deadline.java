public class Deadline extends Task {

    protected String dueDate;

    public Deadline(String description, String newDueDate) {
        super(description);
        this.dueDate = newDueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate);
    }

}
