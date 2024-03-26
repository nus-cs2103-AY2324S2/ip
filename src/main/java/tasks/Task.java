package tasks;

/**
 * The Task class represents a task in the TaskList.
 */
public class Task {

    private String desc;
    private boolean isDone;
    private TaskType type;
    private String start;
    private String end;

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

    /**
     * Constructor for the todo Task.
     * @param desc
     * @param isDone
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.TODO;
    }

    /**
     * Constructor for the deadline task.
     * @param desc
     * @param isDone
     * @param start
     */
    public Task(String desc, boolean isDone, String start) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.DEADLINE;
        this.start = start;
    }

    /**
     * Constructor for the event Task
     * @param desc
     * @param isDone
     * @param start
     * @param end
     */
    public Task(String desc, boolean isDone, String start, String end) {
        this.desc = desc;
        this.isDone = isDone;
        this.type = TaskType.EVENT;
        this.start = start;
        this.end = end;
    }


    /**
    * Returns the task description.
    * @return the task description
    */
    public String getDesc() {
        return desc;
    }

    /**
     * Returns true if the task is done, false otherwise.
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the type of the task.
     * @return the type of the task
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Returns the start date/time of the task.
     * @return the start date/time of the task
     */
    public String getStart() {
        return start;
    }

    /**
     * Returns the end date/time of the task.
     * @return the end date/time of the task
     */
    public String getEnd() {
        return end;
    }

    /*
     * used to mark a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /*
     * used to unmark a task as not done.
     */
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
