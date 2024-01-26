public class Deadline extends Task {
    private String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        return String.format("D | %s | %s", fileLine.substring(5), this.byDate);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.byDate);
    }
}