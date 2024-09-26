package duke;

import java.time.LocalDateTime;

/**
 * Represents a task with a Deadline
 */
public class DeadlineTask extends Task {

    protected String deadline;

    /**
     * Constructor for creating a deadline task with LocalDateTime deadline.
     *
     * @param description description of the deadline task
     * @param deadline    the deadline of the task as LocalDateTime
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = DateHandler.objDateTime(deadline);
    }

    /**
     * Copy constructor to duplicate a DeadlineTask object.
     */
    public DeadlineTask(DeadlineTask deadlineTask) {
        super(deadlineTask);
        this.deadline = deadlineTask.deadline;
    }

    /**
     * Constructor with completion status set to false.
     *
     * @param description description of the deadline task
     * @param deadline    the deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Constructor to allow setting of completion status.
     *
     * @param description description of the deadline task
     * @param isDone      completion status of the task
     * @param deadline    the deadline of the task
     */
    public DeadlineTask(String description, Boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Converts DeadlineTask object to database form.
     *
     * @param deadlineTask the DeadlineTask object
     * @return String the database representation of the DeadlineTask
     */
    public static String outputDeadline(DeadlineTask deadlineTask) {
        String done = deadlineTask.isComplete ? "1" : "0";
        String description = deadlineTask.taskDescription;
        String deadline = deadlineTask.deadline;
        return "D" + " | " + done + " | " + description + " | " + deadline;
    }

    /**
     * Converts stored deadline task to DeadlineTask object.
     *
     * @param dbDeadline the string form of the deadline task in the database storage
     * @return DeadlineTask the DeadlineTask object
     */
    public static DeadlineTask storageDeadline(String dbDeadline) {
        String[] para = dbDeadline.split(" \\| ");
        Boolean isCompleted = para[1].equals("1") ? true : false;
        String description = para[2];
        String deadline = para[3];
        return new DeadlineTask(description, isCompleted, deadline);
    }

    public static void main(String[] args) {
        String taskDeadline = "D | 0 | test task | July 30th"; // Sample deadline task
        DeadlineTask deadlineTask = DeadlineTask.storageDeadline(taskDeadline);
        deadlineTask.markDone();
        System.out.println(deadlineTask);
        System.out.println(DeadlineTask.outputDeadline(deadlineTask));
    }
}
