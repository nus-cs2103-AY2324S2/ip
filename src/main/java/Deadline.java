public class Deadline extends Task {
    private String dueDate;
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
