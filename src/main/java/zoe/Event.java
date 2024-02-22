package zoe;

/**
 * Subclass of task
 * Creates an event when keyed in the form: event XYZ /from 123 /to 123
 */
public class Event extends Task {
    private static int DESCRIPTION = 0;

    private static int TIME_FRAME = 1;
    private static int FROM = 0;
    private static int TO = 1;
    protected String from;
    protected String to;
    public Event(String desc) {
        String[] dsc = desc.split("/from");
        this.description = dsc[DESCRIPTION];
        String[] timeFrame = dsc[TIME_FRAME].split("/to");
        this.from = timeFrame[FROM].trim();
        this.to = timeFrame[TO].trim();
        this.type = "E";
        this.isDone = false;
        this.priority = TaskPriority.EVENT.getPriority();
    }

    public Event(String desc, String isDoneNumber) {
        String[] dsc = desc.split("/from");
        this.description = dsc[DESCRIPTION];
        String[] timeFrame = dsc[TIME_FRAME].split("/to");
        this.from = timeFrame[FROM].trim();
        this.to = timeFrame[TO].trim();
        this.type = "E";
        int doneState = Integer.parseInt(isDoneNumber);
        assert doneState < 2 : "Data file corrupted, invalid state";
        assert doneState >= 0 : "Data file corrupted, invalid state";
        this.isDone = doneState == DONE_STATE;
        this.priority = TaskPriority.EVENT.getPriority();
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(from: %s to: %s)", this.type, this.getStatusIcon(),
                this.description, this.from, this.to);
    }

    @Override
    public String saveTask() {
        return String.format("event_%s/from %s/to %s_%d", this.description, this.from, this.to, this.isDoneNumerical());
    }
}