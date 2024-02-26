package duke.task;

public class Todo extends Task {
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

    public String getTypeOfTask() { return this.taskType; }

    public String printOutput() {
        StringBuilder sb = new StringBuilder();
        if (null != taskType && !taskType.isBlank()) {
            sb.append("[").append(getTypeOfTask()).append("]");
        }
        if (isMarked()) {
            sb.append("[X] ");
        } else {
            sb.append("[ ] ");
        }
        sb.append(getTaskName());
        return sb.toString();
    }

    public String getStringRepresentation(){
        StringBuilder sb = new StringBuilder();
        //sb.append(getSeqNo()).append(" | ");
        if (null != taskType && !taskType.isBlank()) {
            sb.append("[").append(getTypeOfTask()).append("]").append(" | ");
        }
        if (isMarked()) {
            sb.append("[X]").append(" | ");
        } else {
            sb.append("[X]").append(" | ");
        }
        sb.append(getTaskName());
        return sb.toString();
    }
}
