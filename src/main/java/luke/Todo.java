package luke;

public class Todo extends Task {

    /**
     * Creates a Todo with the given name.
     *
     * @param name The name of the Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the full status of the task.
     *
     * @return The status of task. ([taskType][isDone] taskname)
     */
    @Override
    public String getFullStatus() { //TODO: add type of task later
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
