package chingu.task;

/**
 * This class is Task that consist of different types such as ToDos, Deadline and Events
 */
public class Task {
    protected String description;
    public boolean isDone;

    public String priority;

    /**
     * Create instance of the Task which contains description and isDone
     * @param description
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
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

    public String getPriority() {
        return priority;
    }

    public boolean findingKeyword(String keyword) {
        if (description.contains(keyword)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + describeTask() + " Priority: " + getPriority();
    }

}
