public class Deadline extends Task{
    private String taskType = "D";
    private String by = "";
    public Deadline() {
        super();
    }

    public Deadline(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    public Deadline(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    Deadline(String taskName, boolean marked, int seqNo, String type, String by) {
        this(taskName, marked, seqNo, type);
        this.by = by;
    }

    public String getTaskType() { return this.taskType; }

    public String getBy() { return this.by; }

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
        output = output + "(by: "+this.by+")";
        return output;
    }
}
