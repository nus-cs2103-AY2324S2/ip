public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);

        this.by = by;
    }

    public String getTime() {
        return this.by;
    }

    @Override
    public String toString(){
        return "[D] " +  super.toString() + " (by:" + by + ")";
    }

}
