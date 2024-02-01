public class Deadline extends Task {

    private String deadline;
    public Deadline(String description) throws DukeException {
        String[] command = description.split(" /by ");
        if (command.length <= 1) {
            throw new DukeException("____________________________________________________________\n" +
                    "OOPS! Your Only Friend cannot take in a deadline entry with no time :(\n" +
                    "____________________________________________________________\n");
        }
        this.deadline = command[1];
        this.description = command[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.description + " (by: " + this.deadline + ")";
    }
}
