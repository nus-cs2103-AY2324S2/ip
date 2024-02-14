package duke.task;

public class HistoryTask {
    String command;
    Task task;

    public HistoryTask(String command, Task task) {
        this.command = command;
        this.task = task;
    }

    public String getCommand() {
        return command;
    }

    public Task getTask() {
        return task;
    }
}
