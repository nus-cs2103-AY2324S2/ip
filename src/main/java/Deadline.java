public class Deadline extends Task {
    String deadlineDate;
    public Deadline(String descr, String deadline) {
        super(descr);
        this.deadlineDate = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.deadlineDate);
    }
}
