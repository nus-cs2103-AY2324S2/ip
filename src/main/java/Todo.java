public class Todo extends Task {
    Todo(String desc) {
        super(desc);
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
