package objects;

public class DukeException extends Exception {

    public DukeException(String message) {
        super("ERROR! " + message);
    }
}

class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Invalid command");
    }
}

class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("Index is out of bounds");
    }
}

class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Input in the form:\ndeadline {taskName} /by {dueDate}");
    }
}

class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Input in the form:\nevent {taskName} /from {startDate} /to {endDate}");
    }
}