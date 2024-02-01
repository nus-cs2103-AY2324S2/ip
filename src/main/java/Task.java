/**
 * Encapsulates the data and behaviour of a Task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Task {
    private String name;
    private boolean completed;

    /**
     * Constructs a new Task with the given name.
     *
     * @param name The name or descriptor of the task.
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.completed = false;
    }

    /**
     * Returns a string description of the task name and its completion status.
     *
     * @return A String describing the task name and completion status.
     */
    public String printTask() {
        String taskMessage = this.completed ? "[X]" : "[ ]";
        taskMessage += this.name;
        return taskMessage;
    }

    /**
     * Returns a string description of the task name and its completion status in the correct format of the save file.
     *
     * @return A String describing the task name and completion status.
     */
    public String saveTask() {
        String taskMessage = this.completed ? "1" : "0";
        taskMessage += " |" + this.name;
        return taskMessage;
    }
}

