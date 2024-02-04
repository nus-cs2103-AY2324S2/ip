/**
 * Represents a task in the Bond task management program.
 * 
 * @author Benny Loh
 * @version 0.1
 */
package bond.task;

public abstract class Task {

    private Boolean isCompleted;
    private String name;

    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public void markAsComplete() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return String.format(" [%s] %s", this.isCompleted ? "X" : " ", this.name.trim());
    }
}
