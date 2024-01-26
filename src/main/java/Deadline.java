public class Deadline extends Task {
    String dueDate;
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }
    @Override
    public String getRep() {
        return String.format("[D]%s (by:%s)", super.getRep(), this.dueDate);
    }
}
