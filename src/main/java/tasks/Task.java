package tasks;

/**
 * The Task class represents a task in the TaskList.
 */
public class Task {

    private String desc;
    public boolean isDone;
    private TaskType type;
    private String start, end;

    /**
     * The TaskType enum represents the type of task.
     */
    public enum TaskType {
        TODO('T'), DEADLINE('D'), EVENT('E');

        private char code;

        TaskType(char code) {
            this.code = code;
        }

        public char getCode() {
            return code;
        }
    }

    // Constructor for Todo task
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.TODO;
    }

    // Constructor for Deadline task
    public Task(String desc, boolean isDone, String start) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.DEADLINE;
        this.start = start;
    }

    // Constructor for Event task
    public Task(String desc, boolean isDone, String start, String end) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.EVENT;
        this.start = start;
        this.end = end;
    }

    /*
     * mark() and unmark() methods are used to mark and unmark a task as done respectively.
     */
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of the task.
     * @return a String representation of the task
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        taskString.append("[").append(type.getCode()).append("]");
        taskString.append(isDone ? "[X]" : "[ ]");

        // Append task description
        taskString.append(" ").append(desc);

        // Append date/time information if available
        if (type == TaskType.DEADLINE) {
            taskString.append(" (").append(start).append(")");
        } else if (type == TaskType.EVENT) {
            taskString.append(" (").append(start).append(" to ").append(end).append(")");
        }

        return taskString.toString();
    }
}
