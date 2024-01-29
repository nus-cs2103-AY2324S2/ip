/**
 * Represents a task. A <code>Task</code> object contains its
 * description or name, represented by a String, and a boolean
 * indicating whether the task is marked.
 */
public class Task {
    public String taskname;
    public boolean marked;

    /**
     * Basic constructor
     * @param taskname name of task to be tracked
     */
    public Task(String taskname) {
        this.taskname = taskname;
        this.marked = false;
    }
    /**
     * This method indicates this task as marked, by setting the
     * boolean flag to be true.
     */
    public void mark() {
        this.marked = true;
    }
    /**
     * This method indicates this task as unmarked, by setting the
     * boolean flag to be false.
     */
    public void unmark() {
        this.marked = false;
    }
    @Override
    public String toString() {
        return (this.marked ? "[X] " : "[ ] ") + this.taskname;
    }
}
