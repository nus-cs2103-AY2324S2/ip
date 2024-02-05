public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by.trim();
        if (description.trim().length() == 0 || by.trim().length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (by.trim().length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
        }
    }
    public Deadline(String logic, String description, String by) throws DukeException {
        super(description);
        this.by = by.trim();
        if (description.trim().length() == 0 || by.trim().length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (by.trim().length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
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

    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}