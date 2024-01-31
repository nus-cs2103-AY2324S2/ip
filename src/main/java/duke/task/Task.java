package duke.task;

public abstract class Task {
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public void changeMark(String command) {
        isDone = command.equals("MARK");
    }

    public boolean getStatus() {
        return isDone;
    }

    public String formatTask() {
        return task;
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s", mark, task);
    }
}
