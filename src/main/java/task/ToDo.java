package task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    @Override
    public String fileRepresentation() {
        return String.format("T | %s | %s",
                this.getStatusIcon(),
                this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
