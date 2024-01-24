public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[D][X] ";
        } else {
            checkbox = "[D][ ] ";
        }
        return checkbox + name + "(by " + by + ")";
    }
}
