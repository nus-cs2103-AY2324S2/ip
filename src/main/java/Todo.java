public class Todo extends Task {
    public Todo(String n) {
        super(n);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
