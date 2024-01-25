import java.util.HashMap;

public class Task {

    private String desc;
    private boolean isDone;
    private TaskType type;
    private String start, end;

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

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

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
