/**
 * A Task object containing the name of the task
 * and whether the task is completed.
 */
public class Task {
    // Whether the task is completed.
    protected boolean isDone = false;

    /**
     * Constructor for a Task object.
     */
    public Task() { }

    /**
     * Constructor for loading from file.
     *
     * @param b isDone
     */
    public Task(boolean b) {
        this.isDone = b;
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
     * Add a task to the list and print out the details.
     *
     * @param num task number
     */
    public void printAddTask(int num) {
        System.out.println("Oink! Nice I have added this task:\n"
                + " >> " + this + "\nOink's task count: " + num);
    }

    /**
     * Removes a task from the list and print out the details.
     *
     * @param num task number
     */
    public void printDeleteTask(int num) {
        num--;
        System.out.println("Oink! Yosh I have removed this task:\n"
                + " >> " + this + "\nOink's task count: " + num);
    }

    /**
     * toString method for printing task description.
     *
     * @return task status + task name
     */
    @Override
    public String toString() {
        return isDone ? "[x]" : "[ ]";
    }
}
