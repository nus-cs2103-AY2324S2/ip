package Tasks;

public class Deadline extends Task {
    public Deadline(String description) {
        super(description);
        String[] descriptionParsed = description.split("/by");
        super.description = descriptionParsed[0].trim() + " (by: " + descriptionParsed[1].trim() + ")";
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description;
    }
}
