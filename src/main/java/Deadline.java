public class Deadline extends Task {
    private String doBy;

    Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    Deadline(String name, String doBy, boolean done) {
        super(name, done);
        this.doBy = doBy;
    }

    @Override
    public String fileRepresentation() {
        return String.format("D | %s | %s | by: %s",
                this.getStatusIcon(),
                this.getDescription(),
                this.doBy);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doBy);
    }
}
