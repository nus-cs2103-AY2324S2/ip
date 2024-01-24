package Tasks;

public class Event extends Task {
    public Event(String description) {
        super(description);
        String[] descriptionParsed = description.split("/from");
        String[] dateParsed = descriptionParsed[1].split("/to");
        super.description = descriptionParsed[0].trim() + " (from: " + dateParsed[0].trim() + " to: " + dateParsed[1].trim()+ ")";
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description;
    }
}
