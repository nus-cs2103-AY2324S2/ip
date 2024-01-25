class Events extends Task {
    private final String startDate;
    private final String endDate;
    public Events(String description, String startDate, String endDate) {
        super(description, 'E');
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s) \n", this.startDate, this.endDate);
    }
}