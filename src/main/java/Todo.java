public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String print() {
        String str = "[T]" + "[" + super.getStatusIcon() + "] " + super.description;
        return str;
    }
}
