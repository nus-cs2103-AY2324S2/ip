public class Deadline extends Task {
    public Deadline(String description, String endTime) {
        super(description, "", endTime, "deadline");
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), super.getEndTime());
    }
}
