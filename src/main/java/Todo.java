public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String getType() {
        return "[T]";
    }

}