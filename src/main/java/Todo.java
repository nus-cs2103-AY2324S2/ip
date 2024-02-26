public class Todo extends Task{
    private String taskType = "T";
    public Todo() {
        super();
    }

    public Todo(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    public Todo(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    public String getTaskType() { return this.taskType; }

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
        return output;
    }
}
