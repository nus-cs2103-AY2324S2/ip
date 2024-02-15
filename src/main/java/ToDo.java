/*
 * This class represents a ToDo that can be recorded in the tasklist.
 */
class ToDo extends Task {
    /*
     * A constructor for creating a new todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /*
     * A method that returns the task description.
     *
     * @return A label [T] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}