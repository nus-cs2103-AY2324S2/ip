/**
 * A Task object containing the name of the task
 * and whether the task is completed.
 */
public class Task {
    // Task name
    protected String name = "";
    // Whether the task is completed.
    protected boolean isDone = false;

    /**
     * Constructor for a Task object.
     *
     * @param name task name
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task done and prints out that the user has completed the task.
     */
    public void markDone() {
        isDone = true;
        System.out.println("Oink! You have completed this task! Nice nice nice\n"
                + " >> " + this);
    }

    /**
     * Unchecks the task and prints out that the user has unmarked the task.
     */
    public void markUndone() {
        isDone = false;
        System.out.println("Oink! You have unmarked this task! Why why why\n"
                + " >> " + this);
    }

    /**
     * toString method for printing task description.
     * @return task status + task name
     */
    @Override
    public String toString() {
        return isDone ? "[x] " + this.name : "[ ] " + this.name;
    }
}
