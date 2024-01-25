public class TodoTask extends Task {

    /**
     * Constructs a to-do task with the given name.
     *
     * @param name The name of the to-do task.
     * @throws DukeException If the name of the to-do task is an empty string.
     */
    public TodoTask(String name) throws DukeException{
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
