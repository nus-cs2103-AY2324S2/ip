public class Deadline extends Task {
    private String taskType = "D";
    private String by = "";
    Deadline() {
        super();
    }

    Deadline(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    Deadline(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    Deadline(String taskName, boolean marked, int seqNo, String type, String by) {
        this(taskName, marked, seqNo, type);
        this.by = by;
    }

    public String getTypeOfTask() { return this.taskType; }

    public String getBy() { return this.by; }

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
        output = output + "(by: "+this.by+")";
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
        sb.append("(by: ").append(this.by).append(")");
        return sb.toString();
    }

}
