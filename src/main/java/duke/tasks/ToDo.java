package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        int statusValue = this.getStatus() ? 1 : 0;

        return String.format("todo~%d~%s", statusValue,
                this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
