public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[T][X] ";
        } else {
            checkbox = "[T][ ] ";
        }
        return checkbox + name;
    }
}
