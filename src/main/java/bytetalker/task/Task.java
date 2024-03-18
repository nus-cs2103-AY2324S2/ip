package bytetalker.task;

/**
 * Represents the task that user can type in to store in the list. It is the parent class of all types of tasks
 * supprted by the chatbot.
 *
 * @author Junseo Kim
 * @version 1.0
 * @since 2024-02-06
 */
public abstract class Task {
    private String task;
    private boolean isDone;
    private TaskType taskType;
    public Task(TaskType taskType, String task) {
        this.taskType = taskType;
        this.isDone = false;
        this.task = task;
    }

    public Task(TaskType taskType, String task, boolean isDone) {
        this.taskType = taskType;
        this.task = task;
        this.isDone = isDone;
    }

    public String getTask() {
        return this.task;
    }

    public abstract String toString();

    /**
     * Returns the status icon of X or empty space depending on the status of the task.
     * If the status is done, returns X or else empty space.
     *
     * @return Status icon.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Sets status of the task depending on the status argument.
     *
     * @param status Boolean value to change the value of status variable.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the current state of the task.
     *
     * @return the current state of the task.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the type of the task among the enums (Todo, Deadline, Event).
     *
     * @return TaskType of the task.
     */
    public TaskType getTaskType() {
        return this.taskType;
    }

    public void updateTask(String content) {
        this.task = content;
    }
}
