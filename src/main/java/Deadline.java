public class Deadline extends Task{
    private String deadline;
    public Deadline(String taskString, String deadline) {
        super(taskString);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + deadline + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) obj;
        return (super.equals(deadline) && this.deadline.equals(deadline.deadline));
    }

}
