package Panna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task{
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");



    public Event(String input, LocalDate start, LocalDate end) {
        super(input, start, end);

    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + getStart().format(df) +  " to " + getEnd().format(df) + ")";
    }

}
