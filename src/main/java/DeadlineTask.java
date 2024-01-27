public class DeadlineTask extends Task {

    protected String end_time;
    DeadlineTask(String name, String end_time) {
        super(name, Type.D);
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        String output;
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + " (by: " + this.end_time + ")\n";
        } else {
            output = "[" + this.type + "]" + "[ ] " + this.name + " (by: " + this.end_time + ")\n";
        }
        return output;
    }
}
