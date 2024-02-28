package goblin.task;


import goblin.task.Task;

public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public void print() {
        System.out.println( "\t" + "[T]" + getStatusIcon() + getDescription());
    }

    @Override
    public String type() {
        return ("[T]");
    }

    @Override
    public String toString() {
        return isDone + " todo " + getDescription();
    }

    @Override
    public String notPrint() {
        return "\t" + "[T]" + getStatusIcon() + getDescription();
    }
}
