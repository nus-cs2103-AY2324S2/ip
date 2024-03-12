package axolotl.task;

import axolotl.parser.Parser;

import java.time.LocalDateTime;

/**
 * A Deadline object which contains a due date (i.e. by)
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    static protected String ALIAS = "D";

    /**
     * Create a deadline with following details:
     * @param taskName
     * @param status
     * @param by
     */
    public Deadline(String taskName, boolean status, LocalDateTime by) {
        super(taskName, status);
        this.by = by;
    }

    /**
     * Return the string representation of this deadline,
     * where status is either 'X' or ' ' and date is in MMM dd yyyy hh:mma.
     * @return [D] [<status>] <task_name> (by: <by>)
     */
    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (by: " + by.format(dateTimeString)  + ")";
    }

    /**
     * Return the string representation of this deadline to be stored in local file,
     * where the status is in string format (true/false) and date is in dd/MM/yyyy HHmm format.
     * @return D,<status (true/false)>,<task_name>,<from>,<to>
     */
    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + by.format(Parser.dateTimeFormatter);
    }
}
