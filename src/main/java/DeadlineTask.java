public class DeadlineTask extends Task {
    
    private String timing;

    DeadlineTask(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        return "[D]" + status + " " + super.getDescription() + " (by: " + this.timing + ")";
    }

}
