public class Todo extends Task {
    String type = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.type + this.display + " " + this.description;
    }
}
