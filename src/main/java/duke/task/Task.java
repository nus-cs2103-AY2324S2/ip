package duke.task;

/**
 * This class is Task that consist of different types such as ToDos, Deadline and Events
 */
public class Task {
    protected String description;
    public boolean isDone;

    /**
     * Create instance of the Task which contains description and isDone
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Helps to check the status of Task (isDone)
     * @return "X" if task is done or " " otherwise
     */

    public String describeTask() {
        return description;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by making isDone to be true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as undone by making isDone to be true
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns String of task detail with description of the task and whether it is done
     * @return
     */

    public boolean findingKeyword(String keyword) {
        if (description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + this.description;
    }

}
