public class EventTask extends Task{
    private final String from;
    private final String to;

    /**
     * Constructs an event task with the given name, start date/time, and end date/time.
     *
     * @param name The name of the event task.
     * @param from The start date/time of the event task.
     * @param to The end date/time of the event task.
     * @throws DukeException If the name, start date/time, or end date/time of the task is an empty string.
     */
    public EventTask(String name, String from, String to) throws DukeException{
        this(name, from, to, false);
    }

    /**
     * Constructs an event task with the given name, start date/time, end date/time, and done status.
     *
     * @param name The name of the event task.
     * @param from The start date/time of the event task.
     * @param to The end date/time of the event task.
     * @param isDone The done status of the event task.
     * @throws DukeException If the name, start date/time, or end date/time of the task is an empty string.
     */
    public EventTask(String name, String from, String to, boolean isDone) throws DukeException{
        super(name);
        if (name.isEmpty()) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (from.isEmpty()) {
            throw new DukeException("eh the event needs a start date/time");
        }
        if (to.isEmpty()) {
            throw new DukeException("eh the event needs a end date/time");
        }
        this.from = from;
        this.to = to;
        this.markDone(isDone);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getName(), from, to);
    }
}
