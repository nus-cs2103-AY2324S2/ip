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
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (deadline.equals("")) {
            throw new DukeException("bro this task needs a deadline bro");
        }
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
