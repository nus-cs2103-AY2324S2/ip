package zoe;

/**
 * Subclass of task
 * Creates an event when keyed in the form: event XYZ /from 123 /to 123
 */
public class Event extends Task {
    private static int DESCRIPTION = 0;
    private static int FROM = 1;
    private static int TO = 2;
    protected String from;
    protected String to;
    public Event(String desc) {
        String[] str = desc.split("/");
        this.description = str[DESCRIPTION];
        this.from = str[FROM].split(" ",2)[1];
        this.to = str[TO].split(" ", 2)[1];
        this.type = "E";
        this.isDone = false;
        this.priority = TaskPriority.EVENT.getPriority();
    }

    public Event(String desc, String isDoneNumber) {
        String[] str = desc.split("/");
        this.description = str[DESCRIPTION];
        this.from = str[FROM].split(" ",2)[1];
        this.to = str[TO].split(" ", 2)[1];
        this.type = "E";
        assert Integer.parseInt(isDoneNumber) < 2 : "Data file corrupted, invalid state";
        assert Integer.parseInt(isDoneNumber) >= 0 : "Data file corrupted, invalid state";
        this.isDone = isDoneNumber.equals(DoneStates.DONE);
        this.priority = TaskPriority.EVENT.getPriority();
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(from: %sto: %s)", this.type, this.getStatusIcon(),
                this.description, this.from, this.to);
    }

    @Override
    public String saveTask() {
        return String.format("event_%s/from %s/to %s_%d", this.description, this.from, this.to, this.isDoneNumerical());
    }
}