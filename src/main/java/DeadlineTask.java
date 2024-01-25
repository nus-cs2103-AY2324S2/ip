public class DeadlineTask extends Task{
    private final String deadline;

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
