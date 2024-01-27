public class Todo extends Task {
    public Todo(String n) {
        super(n);
    }

    public Todo(String n, boolean d) {
        super(n, d);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
