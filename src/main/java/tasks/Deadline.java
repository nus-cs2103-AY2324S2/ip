package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import Parser.Parser;

public class Deadline extends Task {
    protected LocalDateTime by;

    
    public Deadline(String description, String by) {
        super(description);
        LocalDateTime temp = Parser.stringToDateTime(by);
        this.by = temp;
    }

    public Deadline(String description, String by, String isDone) {
        super(description);
        LocalDateTime temp = Parser.stringToDateTime(by);
        this.by = temp;
        if(isDone.equals("0")){
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")" ; 
    } 

    @Override
    public String toStore() {
        return "D" + " | " + super.getStatusInteger() + " | " + super.toString() + " | " + by + "\n" ; 
    }
}
