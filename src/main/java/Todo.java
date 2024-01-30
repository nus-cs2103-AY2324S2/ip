public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean done) {
        super(description, done);
    }

    @Override
    public String getStorageString() {
        return String.format("T | %s", super.getStorageString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
