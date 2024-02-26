public class Event extends Task{
    private String taskType = "E";
    private String from = "";
    private String to = "";
    public Event() {
        super();
    }

    public Event(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    public Event(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    Event(String taskName, boolean marked, int seqNo, String type, String from, String to) {
        this(taskName, marked, seqNo, type);
        this.from = from;
        this.to = to;
    }

    public String getTaskType() { return this.taskType; }

    public String getFrom() { return this.from; }

    public String getTo() { return this.to; }
    public String printOutput() {
        String output = getSeqNo() +".";
        if (null != taskType && !taskType.isBlank()) {
            output = output +"["+this.getTaskType()+"]";
        }
        if (isMarked()) {
            output = output + "[X] ";
        } else {
            output = output + "[ ] ";
        }
        output = output + getTaskName();
        output = output + "(from: "+this.from +" to: "+this.to+")";
        return output;
    }
}
