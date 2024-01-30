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

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeException e) {
            System.out.println("Invalid date/time format. Please use: yyyy-MM-dd HHmm");
            throw e;
        }
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}

