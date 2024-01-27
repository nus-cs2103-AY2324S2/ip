public class Task {
    private boolean isDone;
    private String taskString;

    public Task(String taskString) {
        this.isDone = false;
        this.taskString = taskString;
    }

    public void markDone() {
        if (isDone) {
            System.out.println("Task is already done!");
        } else {
            System.out.println("OK, task has been marked :)");
            isDone = true;
        }
    }

    public void unmarkDone() {
        if (!isDone) {
            System.out.println("Task is already incomplete!");
        } else {
            System.out.println("OK, task has been unmarked :)");
            isDone = false;
        }
    }

    @Override
    public String toString() {
        char completeTick = isDone ? 'X' : ' ';
        return "[" + completeTick + "] " + taskString;
    }
}
