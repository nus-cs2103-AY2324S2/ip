package snomtask;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.deadline;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            boolean isDateMatch = this.deadline.equals(d.deadline);
            boolean isTaskDescMatch = super.equals(d);
            return isTaskDescMatch && isDateMatch;
        } else {
            return false;
        }
    }


}
