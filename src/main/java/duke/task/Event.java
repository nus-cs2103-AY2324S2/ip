package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event - type of task with from and to date time
 */
public class Event extends Task {
    private String taskType = "E";
    private String from = "";
    private String fromForStorage = "";
    private String to = "";
    private String toForStorage = "";

    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Instantiates a new Event.
     */
    public Event() {
        super();
    }

    /**
     * Instantiates a new Event.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no of task in tasklist
     */
    public Event(String taskName, boolean marked, int seqNo) {
        super(taskName, marked, seqNo);
    }

    /**
     * Instantiates a new Event.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no
     * @param type     the type of task
     */
    public Event(String taskName, boolean marked, int seqNo, String type) {
        super(taskName, marked, seqNo);
        this.taskType = type;
    }

    /**
     * Instantiates a new Event.
     *
     * @param taskName the task name
     * @param marked   true or false indicating whether the task is marked as done or not
     * @param seqNo    the seq no
     * @param type     the type of task
     * @param from     from - date and time in YYYY/MM/DD HH:MM format
     * @param to       to - date and time in YYYY/MM/DD HH:MM format
     */
    public Event(String taskName, boolean marked, int seqNo, String type, String from, String to) {
        this(taskName, marked, seqNo, type);
        this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.fromForStorage = fromDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.toForStorage = toDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    public String getTypeOfTask() { return this.taskType; }

    /**
     * Gets from value of task.
     *
     * @return the start time of task
     */
    public String getFrom() { return this.from; }

    /**
     * Gets to value of task.
     *
     * @return the end time of task
     */
    public String getTo() { return this.to; }
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
        sb.append(" (from: ").append(this.from).append(" to: ").append(this.to).append(")");
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
        sb.append("(from: ").append(this.fromForStorage).append(" to: ").append(this.toForStorage).append(")");
        return sb.toString();
    }
}
