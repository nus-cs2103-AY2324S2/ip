/**
 * Represents a todo task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
class Todo extends Task {

    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}