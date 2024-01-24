package Tasks;

import Exceptions.DukeException;

public class Deadline extends Task {
    public Deadline(String description) throws DukeException {
        super(description);

        String desc = super.description;
        if (!desc.contains("/by")) throw new DukeException("Date missing in description!");

        String[] descParsed = desc.split("/by");

        if (descParsed.length == 1) throw new DukeException("Invalid date!");

        super.description = descParsed[0].trim() + " (by: " + descParsed[1].trim() + ")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
