class Deadlines extends Task {
    private String byDate;

    public Deadlines(String description, boolean isDone, String byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
}