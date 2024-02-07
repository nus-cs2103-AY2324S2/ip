/**
 * Represents a Deadline task, which is a type of task that needs to be
 * completed.
 * Inherits from the Task class.
 */
class Deadline extends Task {
    private String by;

    Deadline(String name, String by) throws DukeException {
        super(name);
        this.by = by;
        if (name == null || name.isEmpty()) {
            throw new DukeException("Task name cannot be empty");
        } else if (by == null || by.isEmpty()) {
            throw new DukeException("Deadline date cannot be empty");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}