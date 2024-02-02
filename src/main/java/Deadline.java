public class Deadline extends Task {
    private String time;
    Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    String getCommand() {
        return "deadline " + name + " /by " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
