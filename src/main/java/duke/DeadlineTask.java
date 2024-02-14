package duke;

import java.time.LocalDateTime;

/**
 * Represents a task with a DeadlineTask.
 */
public class DeadlineTask extends Task {

    protected String deadline;

    /**
     * Default constructor with completion status set to false.
     *
     * @param description description of the deadline task
     * @param deadline    the deadline of the task
     */
    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor allowing setting of completion status.
     *
     * @param description description of the deadline task
     * @param isDone      completion status of the task
     * @param deadline    the deadline of the task
     */
    public DeadlineTask(String description, Boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Overloaded constructor for creating a deadline task with LocalDateTime deadline.
     *
     * @param description description of the deadline task
     * @param deadline    the deadline of the task as LocalDateTime
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = DateHandler.dateTimeToDatabaseString(deadline);
    }

    /**
     * Copy constructor to duplicate a DeadlineTask object.
     */
    public DeadlineTask(DeadlineTask deadlineTask) {
        super(deadlineTask);
        this.deadline = deadlineTask.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    /**
     * Converts database representation of deadline task to DeadlineTask object.
     *
     * @param dbDeadline the string representation of the deadline task in the database
     * @return DeadlineTask the DeadlineTask object
     */
    public static DeadlineTask dbToDeadlineTask(String dbDeadline) {
        String[] para = dbDeadline.split(" \\| ");
        Boolean isDone = para[1].equals("1") ? true : false;
        String description = para[2];
        String deadline = para[3];
        return new DeadlineTask(description, isDone, deadline);
    }

    /**
     * Converts DeadlineTask object to database representation.
     *
     * @param deadlineTask the DeadlineTask object
     * @return String the database representation of the DeadlineTask
     */
    public static String deadlineTaskToDb(DeadlineTask deadlineTask) {
        String done = deadlineTask.isTaskDone ? "1" : "0";
        String description = deadlineTask.taskDescription;
        String deadline = deadlineTask.deadline;
        return "D" + " | " + done + " | " + description + " | " + deadline;
    }

    public static void main(String[] args) {
        String dbDeadline = "D | 0 | return book | July 17th";
        DeadlineTask deadlineTask = DeadlineTask.dbToDeadlineTask(dbDeadline);
        deadlineTask.markDone();
        System.out.println(deadlineTask);
        System.out.println(DeadlineTask.deadlineTaskToDb(deadlineTask));
    }
}
