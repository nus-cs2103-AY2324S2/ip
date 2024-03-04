package task;

/**
 * The Task class contains a task string and a boolean representing if the task is done.
 */
public class Task {
    private boolean isDone;
    private String taskString;

    /**
     * Constructor that initialises the task with the task string and isDone status to false.
     * @param s Task string
     */
    public Task(String s) {
        this.taskString = s.strip();
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String getTaskString() {
        return this.taskString;
    }

    @Override
    public String toString() {
        if (this.getDoneStatus()) {
            return String.format("[X] %s", this.taskString);
        } else {
            return String.format("[ ] %s", this.taskString);
        }
    }

    /**
     * Converts the isDone status of a task to a String of 0 or 1
     * @return String 0 if the task is not done and 1 if the task is done
     */
    public String convertToDataStoreLine() {
        if (this.getDoneStatus()) {
            return "1";
        } else {
            return "0";
        }
    }

    public boolean containsKeyword(String keyword) {
        return this.taskString.contains(keyword);
    }

    public boolean equals(Task task) {
        return this.taskString.equals(task.getTaskString());
    }
}
