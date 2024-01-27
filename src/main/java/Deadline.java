import java.time.LocalDateTime;

public class Deadline extends Task {

    protected String by;

    /**
     * Default constructor, isDone set to false
     * @param description of Deadline
     * @param by is the end of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = Dates.dateTime2DbStr(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the database representation of Deadline to a Deadline Task
     * @param dbDeadline the string rep of Deadline in the database
     * @return Task the Deadline Task object
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
     * Converts the a Deadline Task to the database representation of Deadline
     * @param deadlineTask the Deadline Task object
     * @return Task the string rep of Deadline in the database
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
