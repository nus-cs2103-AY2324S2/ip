package tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
        if (description.equals("")) {
            throw new IllegalArgumentException("Should have more description dawg");
        }

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String formattedString() {
        return "T" + super.formattedString();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}