public class Deadline extends Task{
    private String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s",this.getStatusIcon(), this.getDescription(), this.deadline);
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",super.toString(),this.deadline);
    }
}
