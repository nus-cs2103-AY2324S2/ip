public class DeadlineTask extends Task{
    private final String deadline;

    /**
     * Constructs a deadline task with the given name and deadline.
     *
     * @param name     The name of the deadline task.
     * @param deadline The deadline of the task.
     * @throws DukeException If the name or deadline of the task is an empty string.
     */
    public DeadlineTask(String name, String deadline) throws DukeException {
        this(name, deadline, false);
    }

    /**
     * Constructs a deadline task with the given name, deadline, and done status.
     *
     * @param name     The name of the deadline task.
     * @param deadline The deadline of the task.
     * @param isDone   The done status of the deadline task.
     * @throws DukeException If the name or deadline of the task is an empty string.
     */
    public DeadlineTask(String name, String deadline, boolean isDone) throws DukeException {
        super(name);
        if (name.isEmpty()) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (deadline.isEmpty()) {
            throw new DukeException("bro this task needs a deadline bro");
        }
        this.deadline = deadline;
        this.markDone(isDone);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone() ? 1 : 0, getName(), deadline);
    }
}
