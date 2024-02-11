package Panna;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public Deadline(String input, LocalDate dt) {
        super(input, dt);
      


    }

    @Override
    public String toString() {
        return super.toString() + " (By: " + getDeadline().format(df) + ")";
    }
}
