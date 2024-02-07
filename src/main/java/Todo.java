public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isDone) {
        super(name);
        this.isDone = isDone;
    }

    @Override
    public String saveToFileString() {
        return "T | " + super.saveToFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
