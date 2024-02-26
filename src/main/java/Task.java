public class Task {
    private String taskName = "";
    private boolean marked = false;
    private int seqNo = -1;

    private String taskType = "";

    Task(){
    }
    Task(String name, boolean marked, int seq) {
        this.setTaskName(name);
        this.setMarked(marked);
        this.setSeqNo(seq);
    }

    public String printOutput() {
        String output = getSeqNo() +".";
        if (null != taskType && !taskType.isBlank()) {
            output = output +"["+getTaskType()+"]";
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

    public String getTaskType() { return taskType; }
}
