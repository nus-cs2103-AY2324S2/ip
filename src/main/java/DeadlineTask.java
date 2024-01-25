class DeadlineTask extends Task {
    String date;

    public DeadlineTask(String taskName, String date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", getType(), done ? "X" : " ", taskName, date);
    }
}