public class Deadline extends Task {
    public Deadline(String description, boolean isDone, String endTime) {
        super(description, isDone, "", endTime, TaskType.DEADLINE);
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), super.getEndTime());
    }
}
