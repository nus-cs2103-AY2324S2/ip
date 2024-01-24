public class EventTask extends Task {

    protected String start_time, end_time;
    EventTask(String name, String start_time, String end_time) {
        super(name, Type.E);
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
