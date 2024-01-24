public abstract class Task {
    protected boolean isDone;
    protected String taskDescription;
    private static int total = 0;

    public Task(String taskDescription) {
        total++;
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public static int getTotal() {
        return total;
    }

    public static void setTotal(int total) {
        Task.total = total;
    }
}
