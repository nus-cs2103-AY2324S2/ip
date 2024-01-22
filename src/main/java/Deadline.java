public class Deadline extends Task {
    String by;


    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s by:%s", super.toString(), by);
    }
}
