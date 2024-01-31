public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    public String fileSavingString() {
        return "T | " + Integer.toString(super.isDone ? 1 : 0) + " | " + super.description;
    }
}