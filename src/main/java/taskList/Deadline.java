package taskList;

import java.time.LocalDate;

public class Deadline extends Task {

    protected String byDate;

    public Deadline(String item, String byDate) {
        super(item);
        try {
            String[] dateString = byDate.split(" ", 2);
            //LocalDate thedate = LocalDate.parse(dateString[1].trim());
            this.byDate = dateString[0].trim() + ": " + dateString[1].trim();
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + byDate + ")";
    }
}
