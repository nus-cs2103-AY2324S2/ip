public class Deadline extends Task {
    private String dueDate;
    public Deadline(String task, String dueDate) {
        super(task);
        this.dueDate = dueDate;
    }


    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("T | %s | %s | %s", status, super.formatTask(), dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }
}
