class Deadlines extends Task {
    private String byDate;

    public Deadlines(String description, boolean isDone, String byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    public String getByDate() {
        return this.byDate;
    }

    public void setByDate(String newByDate) {
        this.byDate = newByDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }
}