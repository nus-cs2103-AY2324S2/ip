public class Todo extends Task {
    private String taskType = "T";
    Todo() {
        super();
    }

    Todo(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    Todo(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    public String getTypeOfTask() { return this.taskType; }

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
        sb.append(getTaskName());
        return sb.toString();
    }
}
