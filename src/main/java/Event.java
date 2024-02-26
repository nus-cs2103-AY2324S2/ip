public class Event extends Task {
    private String taskType = "E";
    private String from = "";
    private String to = "";
    Event() {
        super();
    }

    Event(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    Event(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    Event(String taskName, boolean marked, int seqNo, String type, String from, String to) {
        this(taskName, marked, seqNo, type);
        this.from = from;
        this.to = to;
    }

    public String getTypeOfTask() { return this.taskType; }

    public String getFrom() { return this.from; }

    public String getTo() { return this.to; }
    public String printOutput() {
        String output = getSeqNo() +".";
        if (null != taskType && !taskType.isBlank()) {
            output = output +"["+this.getTypeOfTask()+"]";
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(getSeqNo()).append(" | ");
        if (null != taskType && !taskType.isBlank()) {
            sb.append(getTypeOfTask()).append(" | ");
        }
        if (isMarked()) {
            sb.append("[X]").append(" | ");
        } else {
            sb.append("[X]").append(" | ");
        }
        sb.append(getTaskName()).append(" | ");
        sb.append("(from: ").append(this.from).append(" to: ").append(this.to).append(")");
        return sb.toString();
    }
}
