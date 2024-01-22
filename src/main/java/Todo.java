public class Todo extends Task{
    public Todo(Boolean status, String detail) {
        super(status, detail);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
