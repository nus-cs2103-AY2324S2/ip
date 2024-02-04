package haro.task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    public ToDo(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String toString() {
        int marked = this.isDone ? 1 : 0;
        return "T | " + marked + " | " + this.task;
    }
}
