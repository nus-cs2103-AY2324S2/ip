public class Deadline extends Task {
    String end;

    public Deadline(String description, String end) {
        super(description);
        this.end = end;
    }

    @Override
    public String toString() {
        String info = String.format("(by: %s)", end);
        return String.format("[T]%s%s %s", this.getStatusIcon(), super.toString(), info);
    }
}
