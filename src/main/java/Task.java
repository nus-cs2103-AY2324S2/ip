public class Task {
    private static int count = 0;
    private int id;
    private String taskName;
    private Boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.id = ++count;
    }

    public int getId() {
        return this.id;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        String checkMark = "[" + (isDone ? "X" : " ") + "] ";
        return checkMark + taskName;
    }
}
