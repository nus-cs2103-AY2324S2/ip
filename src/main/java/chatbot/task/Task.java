package chatbot.task;

/**
 * Encapsulates the data and behaviour of a chatbot.task.Task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Constructs a new chatbot.task.Task with the given name.
     *
     * @param name The name or descriptor of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Returns a string description of the task name and its completion status.
     *
     * @return A String describing the task name and completion status.
     */
    public String printTask() {
        String taskMessage = this.isCompleted ? "[X]" : "[ ]";
        taskMessage += this.name;
        return taskMessage;
    }

    /**
     * Returns a string description of the task name and its completion status in the correct format of the save file.
     *
     * @return A String describing the task name and completion status.
     */
    public String saveTask() {
        String taskMessage = this.isCompleted ? "1" : "0";
        taskMessage += " |" + this.name;
        return taskMessage;
    }

    /**
     * Checks if task description contains the specified keyword.
     *
     * @param keyword The keyword to be checked for.
     * @return True if the task description contains the keyword, otherwise false.
     */
    public boolean hasKeyword(String keyword) {
        assert keyword != null : "keyword should not be empty";
        String lowerCaseName = this.name.toLowerCase();
        return lowerCaseName.contains(keyword);
    }
}

