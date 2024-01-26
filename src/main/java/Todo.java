public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    @Override
    public String saveData() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
