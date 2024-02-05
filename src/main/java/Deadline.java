public class Deadline extends Task {
    private String deadline;
    public Deadline(String s, String deadline) {
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("D|%s|%s|%s", super.convertToDataStoreLine(), super.getTaskString(), this.deadline);
    }
}