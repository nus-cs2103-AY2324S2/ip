package Tasks;

import Exceptions.DukeException;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
