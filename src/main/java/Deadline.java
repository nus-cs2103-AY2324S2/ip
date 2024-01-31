public class Deadline extends Task {
    String deadline;
    public Deadline(String name, String deadline, boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[D]%s (by: %s)", super.toString(), deadline);
        return str;
    }

    @Override
    public String convertToText() {
        String str = "";
        str = String.format("%s event %s /by %s", isDone, name, deadline);
        return str;
    }
}
