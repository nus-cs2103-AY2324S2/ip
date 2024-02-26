public class Task {
    private String taskName = "";
    private boolean marked = false;
    private int seqNo = -1;
    private String taskType = "";

    public Task() {
    }
    public Task(String name, boolean marked, int seq) {
        this.setTaskName(name);
        this.setMarked(marked);
        this.setSeqNo(seq);
    }

    public String printOutput() {
        String output = getSeqNo() +".";
        if (null != taskType && !taskType.isBlank()) {
            output = output +"["+getTypeOfTask()+"]";
        }
        if (isMarked()) {
            output = output + "[X] ";
        } else {
            output = output + "[ ] ";
        }
        output = output + getTaskName();
        return output;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    public String getTypeOfTask() { return taskType; }

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
