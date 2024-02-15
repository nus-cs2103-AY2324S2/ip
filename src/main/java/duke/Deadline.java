package duke;

import java.time.LocalDateTime;

/**
 * Deadline is a type of task with a by field that denotes by when it has to be completed.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Default constructor, isDone set to false.
     *
     * @param description of Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, int priority, String by) {
        super(description, priority);
        this.by = by;
    }

    /**
     * Overloaded constructor, isDone set.
     *
     * @param description of Deadline
     * @param isDone sets the completion status of Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, Boolean isDone, int priority, String by) {
        super(description, isDone, priority);
        this.by = by;
    }

    /**
     * Overloaded constructor with LocalDateTime.
     *
     * @param description of Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, int priority, LocalDateTime by) {
        super(description, priority);
        this.by = Dates.dateTime2DbStr(by);
    }

    /**
     * Constructor used to clone a Deadline object
     */
    public Deadline(Deadline deadline) {
        super(deadline);
        this.by = deadline.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the database representation of Deadline to a Deadline Task.
     *
     * @param dbDeadline the string rep of Deadline in the database.
     * @return Task the Deadline Task object
     */
    public static Deadline db2Deadline(String dbDeadline) {
        // D | 0 | 1 | return book | June 6th
        String[] params = dbDeadline.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        int priority = Integer.parseInt(params[2].trim());
        String desc = params[3];
        String by = params[3];
        return new Deadline(desc, isDone, priority, by);
    }

    /**
     * Converts a Deadline Task to the database representation of Deadline.
     *
     * @param deadlineTask the Deadline Task object
     * @return Task the string rep of Deadline in the database
     */
    public static String deadline2Db(Deadline deadlineTask) {
        // D | 0 | 1 | return book | June 6th
        String done = deadlineTask.isDone ? "1" : "0";
        String desc = deadlineTask.description;
        String priority = Integer.toString(deadlineTask.priority);
        String by = deadlineTask.by;
        return "D" + " | " + done + " | " + priority + " | " + desc + " | " + by;
    }

    public static void main(String[] args) {
        String dbDeadline = "D | 0 | 1 | return book | June 6th";
        Deadline deadlineTask = Deadline.db2Deadline(dbDeadline);
        deadlineTask.markAsDone();
        System.out.println(deadlineTask);
        System.out.println(Deadline.deadline2Db(deadlineTask));

        // Test creating a deadline with valid date
        String desc = "Buy Bread";
        String validInputDate1 = "15/01/2023 1430";
        int priority = 5;
        if (Dates.isValidInputDate(validInputDate1)) {
            LocalDateTime validDate1 = Dates.inputStr2DateTime(validInputDate1);
            Deadline d = new Deadline(desc, priority, validDate1); // Create date object
            d.markAsDone();
            System.out.println(Deadline.deadline2Db(d));
        }
    }
}
