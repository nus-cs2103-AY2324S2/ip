package duke;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;

    /**
     * Default constructor, isDone set to false
     * @param description of duke.Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor, isDone can be set
     * @param description of duke.Deadline
     * @param isDone sets the completion status of Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Overloaded constructor
     * @param description of duke.Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = Dates.dateTime2DbStr(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the database representation of duke.Deadline to a duke.Deadline duke.Task
     * @param dbDeadline the string rep of duke.Deadline in the database
     * @return duke.Task the duke.Deadline duke.Task object
     */
    public static Deadline db2Deadline(String dbDeadline) {
        // D | 0 | return book | June 6th
        String[] params = dbDeadline.split(" \\| ");
        Boolean isDone = params[1].equals("1") ? true : false; // if "1", means isDone
        String desc = params[2];
        String by = params[3];
        return new Deadline(desc, isDone, by);
    }

    /**
     * Converts the a duke.Deadline duke.Task to the database representation of duke.Deadline
     * @param deadlineTask the duke.Deadline duke.Task object
     * @return duke.Task the string rep of duke.Deadline in the database
     */
    public static String deadline2Db(Deadline deadlineTask) {
        // D | 0 | return book | June 6th
        String done = deadlineTask.isDone ? "1" : "0";
        String desc = deadlineTask.description;
        String by = deadlineTask.by;
        return "D" + " | " + done + " | " + desc + " | " + by;
    }

    public static void main(String[] args) {
        String dbDeadline = "D | 0 | return book | June 6th";
        Deadline deadlineTask = Deadline.db2Deadline(dbDeadline);
        deadlineTask.markAsDone();
        System.out.println(deadlineTask);
        System.out.println(Deadline.deadline2Db(deadlineTask));

        // Test creating a deadline with valid date
        String desc = "Buy Bread";
        String validInputDate1 = "15/01/2023 1430";
        if (Dates.isValidInputDate(validInputDate1)) {
            LocalDateTime validDate1 = Dates.inputStr2DateTime(validInputDate1);
            Deadline d = new Deadline(desc, validDate1); // Create date object
            d.markAsDone();
            System.out.println(Deadline.deadline2Db(d));
        }
    }
}
