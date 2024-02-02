package haro.task;

import haro.task.Task;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }
    public ToDo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String printTask() {
        return "[T]" + super.printTask();
    }

    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "T | " + marked + " | " + this.task;
    }
}
