class EventTask extends Task {
    public String from, to;
    public EventTask(String type, String desc, String from, String to) {
        super(type, desc);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + type + "] "
                + (completed ? "[X]" : "[ ]")
                + " " + desc
                + (type.equals("E") ? " (from: " + from + " to: " + to + ")": "");
    }
}
