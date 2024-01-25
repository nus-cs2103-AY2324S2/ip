public class Deadline extends Task{
    public Deadline(String input, String deadline) {
        super(input, deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (By: " + this.getDeadline() + ")";
    }
}
