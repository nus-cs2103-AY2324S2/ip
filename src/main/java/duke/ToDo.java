package duke;

public class ToDo extends Task {
    public ToDo(String description) throws DukeException {
        super(description);
        if (description.trim().length() == 0) {
            throw new DukeException("Please enter a description for this todo task");
        }
    }
    public ToDo(String logic, String description) throws DukeException {
        super(description);
        if (description.trim().length() == 0) {
            throw new DukeException("Please enter a description for this todo task");
        }
        switch (logic) {
        case "1":
            this.isDone = true;
            break;
        default:
            this.isDone = false;
            break;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
