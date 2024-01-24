public class DeadlineTask extends Task {

    protected String end_time;
    DeadlineTask(String name, String end_time) {
        super(name, Type.D);
        this.end_time = end_time;
    }
}
