class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) throws HenryException {
        super(description);

        if (from.isEmpty() || from.equals(" ")) {
            throw new HenryException("Missing time !!!\n");
        } else if (to.isEmpty() || to.equals(" ")) {
            throw new HenryException("Missing time!!!\n");
        }

        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}