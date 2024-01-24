package Tasks;

import Exceptions.DukeException;

public class Event extends Task {
    public Event(String description) throws DukeException {
        super(description);

        String desc = super.description;
        if (!desc.contains("/from") || !desc.contains("/to")) throw new DukeException("Dates missing in description!");

        String[] descParsed = desc.split("/from");
        String[] dateParsed = descParsed[1].split("/to");

        if (dateParsed.length <= 1) throw new DukeException("Invalid dates!");

        super.description = descParsed[0].trim() + " (from: " + dateParsed[0].trim() + " to: " + dateParsed[1].trim()+ ")";
    }

    @Override
    public String toString() {
        return "[" + TaskType.E + "][" + super.getStatusIcon() + "] " + super.description;
    }
}
