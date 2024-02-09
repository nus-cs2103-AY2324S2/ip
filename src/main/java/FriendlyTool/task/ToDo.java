package FriendlyTool.task;

public class ToDo extends Task {
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T " + super.toSaveFormat() + "\n";
    }

    public boolean equals(ToDo t) {
        return t.getName() == this.getName();
    }
}
