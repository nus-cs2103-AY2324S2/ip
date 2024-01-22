public class Deadline extends Task {
    private String doBy;

    Deadline(String name, String doBy) {
        super(name);
        this.doBy = doBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.doBy);
    }
}
