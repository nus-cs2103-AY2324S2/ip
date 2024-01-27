public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String time) {
        super(name);
        this.deadline = time;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + this.deadline + ")";
    }
}
