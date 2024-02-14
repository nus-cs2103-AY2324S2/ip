package duke.tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String describe() {
        return String.format("[T]%s", super.describe());
    }

    @Override
    public String toStorageString() {
        return String.format("T,%s", super.toStorageString());
    }
}
