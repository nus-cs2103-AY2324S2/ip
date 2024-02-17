public class Todo extends Task{
    public Todo(String description, Boolean isDone) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T| " + super.toFileString();
    }

}
