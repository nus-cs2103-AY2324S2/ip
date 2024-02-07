package chimp.task;
public class Task {
    String text;
    TaskStatus status;

    public Task(String text, TaskStatus status) {
        this.text = text;
        this.status = status;
    }

    public void mark() {
        this.status = TaskStatus.MARKED;
    }

    public void unmark() {
        this.status = TaskStatus.UNMARKED;
    }

    @Override
    public String toString() {
        String mark = status == TaskStatus.MARKED ? "X" : " ";
        return "[" + mark + "] " + text;
    }
}
