package checkbot.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String formatForFile() {
        return "T | " + super.formatForFile();
    }
}
