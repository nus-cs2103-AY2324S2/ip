/**
 * Represents an Event task, which is a type of task that needs to be completed.
 * Inherits from the Task class.
 */
class Event extends Task {
    private String from;
    private String to;

    Event(String name, String from, String to) throws DukeException {
        super(name);
        this.from = from;
        this.to = to;
        if (name == null || name.isEmpty()) {
            throw new DukeException("Task name cannot be empty");
        } else if (from == null || from.isEmpty()) {
            throw new DukeException("From date cannot be empty");
        } else if (to == null || to.isEmpty()) {
            throw new DukeException("To date name cannot be empty");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}