class Deadline extends Task {
    private final String date;
    public Deadline(String description, String date) throws HenryException {
        super(description);

        if (date.isBlank()) {
            throw new HenryException("No due date specified!");
        }

        this.date = date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}