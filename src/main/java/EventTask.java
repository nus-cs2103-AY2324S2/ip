class EventTask extends Task {
    String startDate;
    String endDate;

    public EventTask(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)", getType(), done ? "X" : " ", taskName, startDate, endDate);
    }
}