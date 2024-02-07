package univus.task;
public class ToDo extends Task {
    public ToDo(String description) {
        super(description.replaceFirst("todo ", ""));
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
