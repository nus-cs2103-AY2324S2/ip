package Objects;

/**
 * class which inherits tasks
 */
public class Todo extends Task {
    public Todo(String name,boolean mark) {
        super(name,mark);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toStringFile() {
        return "T|" + super.toStringFile();
    }
}
