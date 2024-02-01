package duke;

import java.time.format.DateTimeFormatter;

public class Task {
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String task;
    private boolean isDone = false;
    private String taskType;

    public Task(String taskDesc) {
        this.task = taskDesc;
    }

    public Task(String taskDesc, String taskType) {
        this.task = taskDesc;
        this.taskType = taskType;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    public String getStatusBinary() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getTask() {
        return this.task;
    }

    public String getTaskType() {
        return "[" + this.taskType + "]";
    }

    public String announcement() {
        return "New task created!";
    }

    public String saveString() {
        return "Type|Done|Description";
    }
    public String getTaskTypeSingle() {
        return this.taskType;
    }
}
