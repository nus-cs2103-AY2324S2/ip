package Tasks;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    protected void format(String description) {
        return; // ToDo tasks do not need to format anything.
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
