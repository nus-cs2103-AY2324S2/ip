public class Deadline extends Task {
    String deadlineDate;
    public Deadline(String descr, String deadline) {
        super(descr);
        this.deadlineDate = deadline;
    }

    public Deadline(String status, String descr, String deadline) {
        super(descr);
        this.deadlineDate = deadline;
        super.setStatus(status);
    }


    @Override
    public String writeObject() {
        return String.format("deadline %s | %s \n", super.writeObject(), this.deadlineDate);
    }
    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.deadlineDate);
    }
}
