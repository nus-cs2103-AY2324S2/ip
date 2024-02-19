package duke.Tasks;


public abstract class Task {
    String task;

    public boolean isMarked;

    public Task(String task) {
        this.task = task;
        this.isMarked = false;

    }

    public String getTask() {
        return task;
    }

    public void markDone() {
        isMarked = true;
    }

    public void markNotDone() {
        isMarked = false;
    }

    public String mark() {
        return (isMarked ? "[X]" : "[ ]");
    }


    public abstract String tag();

    @Override
    public String toString() {
        return mark() + " " + task;
    }
}
