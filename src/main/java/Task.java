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
            System.out.println("OK, this task has been marked :)");
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return (this.taskString.equals(task.taskString));
    }
}
