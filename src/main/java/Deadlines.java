class Deadlines extends Task {
    private final String date;
    public Deadlines(String description, String date) {
        super(description, 'D');
        this.date = date;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)\n", this.date);
    }
}