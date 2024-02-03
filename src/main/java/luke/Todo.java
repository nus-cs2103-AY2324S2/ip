package luke;

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

    @Override
    public boolean equals(Object obj) {
        Todo todo = (Todo) obj;
        if (todo.name.equals(this.name) && (todo.isDone == this.isDone)) {
            return true;
        }
        return false;
    }
}
