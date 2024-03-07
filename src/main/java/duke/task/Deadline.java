package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline - type of Task with deadline
 *
 */
public class Deadline extends Task {
    private String taskType = "D";
    private String by = "";
    private String byForStorage = "";
    private LocalDateTime byDate;

    /**
     * Instantiates a new Deadline.
     */
    public Deadline() {
        super();
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no of the task in tasklist
     */
    public Deadline(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    /**
     * Instantiates a new Deadline.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no
     * @param type     the type of the task
     */
    public Deadline(String taskName, boolean marked, int seqNo, String type) {
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
     * @param by       the due date in YYYY/mm/DD HH:MM format
     */
    public Deadline(String taskName, boolean marked, int seqNo, String type, String by) {
        this(taskName, marked, seqNo, type);
        this.byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.by = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.byForStorage = byDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    public String getTypeOfTask() { return this.taskType; }

    /**
     * Gets due date value.
     *
     * @return the due date in String format
     */
    public String getBy() { return this.by; }

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
        sb.append(" (by: ").append(this.by).append(")");
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
        sb.append("(by: ").append(this.byForStorage).append(")");
        return sb.toString();
    }

}
