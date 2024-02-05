public class Deadlines extends Task{

    protected String by;
    public Deadlines(String task, String by) {
        super(task);
        this.by = by;
    }

    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        return "D | " + done + " | " + super.toString() + " | " + by + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

}
