public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    @Override
    public String getTaskIcon() {
        return "D";
    }
    @Override
    public String ToString() {
        return super.ToString() + " (by: " + by + ")";
    }

    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/" + by;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/" + by;
        }
    }
}
