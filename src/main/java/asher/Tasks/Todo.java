package asher.Tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String writeToString() {
        String status = isDone ? "1" : "0";
        return "T" + " | " + status + " | " + description;
    }
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
