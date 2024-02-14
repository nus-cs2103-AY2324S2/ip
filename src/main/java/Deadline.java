public class Deadline extends Task {
    private String name;
    private String by;
    private boolean done;
    private static String identifier = "[D]";

    public Deadline(String name, String by) {
        super(name);
        this.name = name;
        this.by = by;
        this.done = false;
    }

    @Override
    public String toString() {
        String str = identifier + super.toString() + "(" + by + ")";
        return str;
    }

    public String getInput() {
        String str = String.format("deadline %s /%s", name, by);
        return str;
    }
}
