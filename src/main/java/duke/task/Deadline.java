package duke.task;

//public class Deadline extends Task {
//    String by;
//
//    public Deadline(String description, String by) {
//        super(description);
//        this.by = by;
//    }
//
//    public String type() {
//        return "D";
//    }
//
//    @Override
//    public String toString() {
//        return "[D]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by + ")";
//    }
//
//    public String getBy() {
//        return by;
//    }
//}



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by.format(formatter) + ")";
    }

    public LocalDateTime getBy() {
        return by;
    }
}
