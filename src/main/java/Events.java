class Events extends Task {

    private final String from;
    private final String to;

    Events(String str, String from, String to) {
        super(str);
        this.from = from;
        this.to = to;
    }

    String getStatusIcon() {
        return (this.is_marked ? "[E][X]" : "[E][ ]");
    }

    public String toString() {
        return super.toString() + " (from: " + this.from + " to:" + this.to + ")";
    }

    String added(int length) {
        return "   Got it. I've added this task:\n" + "     " +
                this.getStatusIcon() + " " + this.taskname + "\n" +
                "   Now you have " + String.valueOf(length) + " tasks in the list";
    }
}