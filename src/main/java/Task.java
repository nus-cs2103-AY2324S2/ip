public class Task {

    String desc;
    boolean isDone;

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + desc;
    }
}
