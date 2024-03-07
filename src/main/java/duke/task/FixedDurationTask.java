package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * FixedDurationTask - type of Task which requires given duration of time
 *
 */
public class FixedDurationTask extends Task{
    private String taskType = "D";
    private String duration = "";

    /**
     * Instantiates a new FixedDurationTask.
     */
    public FixedDurationTask() {
        super();
    }

    /**
     * Instantiates a new FixedDurationTask.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no of the task in tasklist
     */
    public FixedDurationTask(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    /**
     * Instantiates a new FixedDurationTask.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no
     * @param type     the type of the task
     */
    public FixedDurationTask(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no
     * @param type     the type of task
     * @param duration the duration required for task
     */
    public FixedDurationTask(String taskName, boolean marked, int seqNo, String type, String duration) {
        this(taskName, marked, seqNo, type);
        this.duration = duration;
    }

    /**
     * Gets duration required for task.
     *
     * @return the duration as a String
     */
    public String getDuration() {
        return this.duration;
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
        sb.append(" (").append(this.duration).append(")");
        return sb.toString();
    }

    public String getStringRepresentation(){
        StringBuilder sb = new StringBuilder();
//        sb.append(getSeqNo()).append(" | ");
        if (null != taskType && !taskType.isBlank()) {
            sb.append("[").append(getTypeOfTask()).append("]").append(" | ");
        }
        if (isMarked()) {
            sb.append("[X]").append(" | ");
        } else {
            sb.append("[ ]").append(" | ");
        }
        sb.append(getTaskName()).append(" | ");
        sb.append("(").append(this.duration).append(")");
        return sb.toString();
    }
}
