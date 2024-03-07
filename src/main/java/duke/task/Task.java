package duke.task;

/**
 * Task - Parent class for all types of tasks
 */
public class Task {
    private String taskName = "";
    private boolean marked = false;
    private int seqNo = -1;
    private final String taskType = "";

    /**
     * Instantiates a new Task.
     */
    public Task() {
    }

    /**
     * Instantiates a new Task.
     *
     * @param name   the name
     * @param marked the marked
     * @param seq    the seq
     */
    public Task(String name, boolean marked, int seq) {
        this.setTaskName(name);
        this.setMarked(marked);
        this.setSeqNo(seq);
    }

    /**
     * Print output string.
     *
     * @return the string
     */
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

    /**
     * Gets task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Sets task name.
     *
     * @param taskName the task name
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Is marked boolean.
     *
     * @return the boolean
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Sets marked.
     *
     * @param marked the marked
     */
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    /**
     * Gets seq no.
     *
     * @return the seq no
     */
    public int getSeqNo() {
        return seqNo;
    }

    /**
     * Sets seq no.
     *
     * @param seqNo the seq no
     */
    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * Gets type of task.
     *
     * @return the type of task
     */
    public String getTypeOfTask() { return taskType; }

    /**
     * Get string representation string.
     *
     * @return the string
     */
    public String getStringRepresentation(){
        StringBuilder sb = new StringBuilder();
//        sb.append(getSeqNo()).append(" | ");
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
