public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatus() + " " + super.toString();
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.description;
    }
}