public class Task {

    private static final String TASK_MESSAGE = "[%s] %s";
    private static final String TASK_FILE_TEMPLATE = "%s | %s";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String taskFileTemplate() {
        return String.format(TASK_FILE_TEMPLATE, getStatusIcon(), description);
    }

    @Override
    public String toString() {
        return String.format(TASK_MESSAGE, getStatusIcon(), this.description);
    }

}
