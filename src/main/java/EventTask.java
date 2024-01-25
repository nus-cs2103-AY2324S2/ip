public class EventTask extends Task{
    private final String from;
    private final String to;

    public EventTask(String name, String from, String to) throws DukeException{
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (from.equals("")) {
            throw new DukeException("eh the event needs a start date/time");
        }
        if (to.equals("")) {
            throw new DukeException("eh the event needs a end date/time");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
