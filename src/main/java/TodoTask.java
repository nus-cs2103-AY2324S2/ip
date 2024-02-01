public class TodoTask extends Task {

    /**
     * Constructs a to-do task with the given name.
     *
     * @param name The name of the to-do task.
     * @throws DukeException If the name of the to-do task is an empty string.
     */
    public TodoTask(String name) throws DukeException{
        super(name);
        if (name.isEmpty()) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
    }

    /**
     * Constructs a to-do task with the given name and done status.
     *
     * @param name The name of the to-do task.
     * @param isDone The done status of the to-do task.
     * @throws DukeException If the name of the to-do task is an empty string.
     */
    public TodoTask(String name, boolean isDone) throws DukeException {
        super(name, isDone);
        if (name.isEmpty()) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getName());
    }
}
