import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private String deadline;

    public Deadline(String deadline, String task) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        try {
            LocalDate ddl = LocalDate.parse(deadline);
            return "(by: " + ddl.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } catch (DateTimeParseException e) {
            return "(by: " + deadline + ")";
        }
    }

    public String isDeadline() {
        return "[D]";
    }

    public String addDeadline() {
        return "Got it. I've added this task:\n"
                + "    " + this.isDeadline() + this.marked() + " "
                + this.getTask()
                + this.getDeadline();
    }

    @Override
    public void writeToFile(File filePath) {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write(this.isDeadline() + this.marked() + " "
                    + this.getTask() + this.getDeadline() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again xx");
        }
    }
}
