public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.trim().length() == 0 || from.trim().length() == 0 || to.trim().length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (from.trim().length() == 0) {
                errorMessage += "\na deadline after the '/from' command";
            }
            if (to.trim().length() == 0) {
                errorMessage += "\na deadline after the '/to' command";
            }
            throw new DukeException(errorMessage);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
