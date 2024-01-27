public class Deadline extends Task {
    private final String deadline;
    Deadline(String content, String deadline) {
        super(content);
        if (deadline.isEmpty()) { //handling the case where deadline does not get a valid deadline
            this.deadline = String.valueOf(MamtaException.invalidDates());
        } else {
            this.deadline = deadline;
        }
    }

    Deadline(boolean isComplete, String content, String deadline) {
        super(isComplete, content);
        this.deadline = deadline;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(true, this.content, this.deadline);
    }
    @Override
    public Deadline unmarkTask() {
        return new Deadline(false, this.content, this.deadline);
    }
    public String toString() {
        if (this.isComplete) {
            return String.format("[D][X] %s(by: %s)", this.content, this.deadline);
        } else {
            return String.format("[D][ ] %s(by: %s)", this.content, this.deadline);
        }
    }
}
