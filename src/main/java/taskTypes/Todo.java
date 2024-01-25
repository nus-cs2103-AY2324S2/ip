package taskTypes;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }
    public String statusString() {
        return String.format("[T]%s", super.statusString());
    }
}

