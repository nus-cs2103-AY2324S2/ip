package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import oop.Ui;

public class Deadline extends Task{
    private LocalDateTime dueDate;
    private static final String line = "\t______________________________________________________";
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, inputFormat);
    }

    @Override
    public String print(){
        String str = "";
        try {
            String time = dueDate.format(outputFormat);
            str = "[D]" + super.print() + "(by: " + time + ")";
        } catch (DateTimeParseException e) {
            System.out.println(line);
            System.out.println("\t I think you haven't had enough vitamin C."
                    + "\n\t Your time format should be :"
                    + "\n\t\t { dd/MM/yyyy HHmm }"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(line);
        }
        return str;
    }
    @Override
    public String getDescription() {
        String time = dueDate.format(outputFormat);
        String str = "[D]" + super.getDescription() + " " + time;
        return str;
    }

    @Override
    public String getTaskInfo() {
        String time = dueDate.format(outputFormat);
        return "[D] " + "/ [" + super.getStatusIcon()
                + "] / " + super.getTaskInfo() + "/ " + time;
    }
}
