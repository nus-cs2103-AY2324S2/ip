import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected String inputDate;
    protected String date;
    public Deadline(String desc) {
        String[] str = desc.split("/by ");
        this.description = str[0];
        this.inputDate = str[1];
        LocalDate d = LocalDate.parse(inputDate);
        this.date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.type = "D";
        this.isDone = false;
    }

    public Deadline(String desc, String isDoneNumber) {
        String[] str = desc.split("/by ");
        this.description = str[0];
        this.inputDate = str[1];
        LocalDate d = LocalDate.parse(inputDate);
        this.date = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.type = "D";

        if (isDoneNumber.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(by:%s)", this.type, this.getStatusIcon(),
                this.description, this.date);
    }

    @Override
    public String saveTask() {
        return String.format("deadline_%s/by %s_%d", this.description, this.inputDate, this.isDoneNumerical());
    }
}
