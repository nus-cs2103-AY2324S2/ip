public class DeadlineTask extends Task {
    
    private String timing;

    DeadlineTask(String description, String timing) {
        super(description);
        this.timing = timing;
    }

    DeadlineTask(String description, String timing, boolean isCompleted) {
        super(description, isCompleted);
        this.timing = timing;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        return "[D]" + status + " " + super.getDescription() + " (by: " + this.timing + ")";
    }

    @Override
    public String exportToSave() {
        String status = super.isCompleted() ? "1" : "0";
        return "D," + status + "," + super.getDescription() + "," + this.timing;
    }

}
