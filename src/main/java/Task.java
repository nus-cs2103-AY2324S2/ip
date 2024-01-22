/**
 * Task is a class with description, can record whether a task is done or not done
 */
public class Task {
    /**
     * Description of the task
     */
    private String description;
    /**
     * Record a task is done or not done
     */
    private boolean isDone;

    /**
     * Constructor of task class
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To mark a task as done
     */
    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this);
    }


    /**
     * To mark a task as undone
     */
    public void markUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(this);
    }


    /**
     * String representation of task
     * @return string representation of task for done and not done task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
