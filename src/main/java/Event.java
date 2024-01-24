public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

// event project meeting /from Mon 2pm /to 4pm
//    ____________________________________________________________
//     Got it. I've added this task:
//       [E][ ] project meeting (from: Mon 2pm to: 4pm)