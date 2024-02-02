public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveFile() {
        return "T" + "|" + super.done() + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}