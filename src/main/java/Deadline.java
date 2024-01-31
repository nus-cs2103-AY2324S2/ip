public class Deadline extends Task {

    private String deadline;
    public Deadline(String description) {
        String[] command = description.split(" /by ");
        this.deadline = command[1];
        this.description = command[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.description + " (by: " + this.deadline + ")";
    }
}
