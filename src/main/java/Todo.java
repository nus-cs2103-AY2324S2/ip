public class Todo extends Task{
    public Todo(String name, Boolean status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return "[T]"+super.toString();
    }
}
