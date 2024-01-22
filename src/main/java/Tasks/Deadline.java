package Tasks;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, boolean status, String deadline) {
        super(description, status, Type.DEADLINE);
        this.deadline = deadline;
    }
}
