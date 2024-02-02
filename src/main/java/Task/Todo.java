package Task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
