public class EventTask extends Task {

    protected String start_time, end_time;
    EventTask(String name, String start_time, String end_time) {
        super(name, Type.E);
        this.start_time = start_time;
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        String output;
        if (done) {
            output = "[" + this.type + "]" + "[X] " + this.name + " (from: " + this.start_time + " to: " + this.end_time + ")\n";
            output = this.end_time;
        } else {
            output = "[" + this.type + "]" + "[X] " + this.name + " (from: " + this.start_time + " to: " + this.end_time + ")\n";
        }
        return output;
    }
}
