//import java.time.DateTimeException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
////public class Deadline extends Task{
////    protected String by;
////    //private LocalDateTime by;
////
////    public Deadline(String description, String by) {
////        super(description);
////        this.by = by;
////    }
////
////    @Override
////    public String toString() {
////        return "[D]" + super.toString() + " (by: " + by + ")";
////    }
////
////
////}
//
//public class Deadline extends Task {
//    private LocalDateTime by;
//
//    public Deadline(String description, LocalDateTime by) {
//        super(description);
//        this.by = by;
//    }
//
//    public LocalDateTime getBy() {
//        return by;
//    }
//
//    @Override
//    public String toString() {
//        return "[D]" + super.toString() + " (by: " +
//                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
//    }
//
//    @Override
//    public String toFileString() {
//        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
//                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
//    }
//}
package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a task with a deadline in the Duke program.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline of the task in the format "yyyy-MM-dd HHmm".
     * @throws DateTimeException If there is an error parsing the deadline format.
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Invalid date/time format. Please use: yyyy-MM-dd HHmm");
            throw e;
        }
    }
    /**
     * Gets the deadline of the task.
     *
     * @return The LocalDateTime representing the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }
    /**
     * Returns a string representation of the Deadline object.
     *
     * @return A string in the format "[D][Status] Description (by: Deadline)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }
    /**
     * Returns a string representation of the Deadline object for saving to a file.
     *
     * @return A string in the format "D | Status | Description | Deadline".
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

