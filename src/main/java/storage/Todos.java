package storage;

public class Todos extends Task {
    public Todos(String originalCommand, String description) {
        super(originalCommand, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
