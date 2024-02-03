package zoe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of task
 * Creates a deadline when keyed in the form: deadline XYZ /by yyyy-mm-dd
 */
public class Deadline extends Task {

    protected String date;
    protected String formattedDate;
    public Deadline(String desc) {
        String[] str = desc.split("/by ");
        this.description = str[0];
        this.date = str[1];
        LocalDate inputDate = LocalDate.parse(date);
        this.formattedDate = inputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.type = "D";
        this.isDone = false;
    }

    public Deadline(String desc, String isDoneNumber) {
        String[] str = desc.split("/by ");
        this.description = str[0];
        this.date = str[1];
        LocalDate inputDate = LocalDate.parse(date);
        this.formattedDate = inputDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.type = "D";
        this.isDone = isDoneNumber.equals("1");
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(by:%s)", this.type, this.getStatusIcon(),
                this.description, this.formattedDate);
    }

    @Override
    public String saveTask() {
        return String.format("deadline_%s/by %s_%d", this.description, this.date, this.isDoneNumerical());
    }
}
