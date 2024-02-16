/**
 * Represents a todo task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
class Todo extends Task {

    Todo(String name) throws DukeException {
        super(name);
        if (name == null || name.isEmpty()) {
            throw new DukeException("Task name cannot be empty");
        }
    }

    Todo(String name, boolean doneStatus) throws DukeException {
        super(name, doneStatus);
        if (name == null || name.isEmpty()) {
            throw new DukeException("Task name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}