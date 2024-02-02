public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDataString() {
        return "T | " + (isDone? "1" : "0") + " | " + super.toString();
    }
}
