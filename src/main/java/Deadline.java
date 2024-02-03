import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline implements Item, Serializable {

    private boolean isDone = false;
    private String name = "";
    private LocalDateTime doneBy;
    public Deadline(String[] info) throws CustomExceptions {
        int index = 1;
        String s = "";
        String doneByString = "";
        while (!info[index].equals("/by")) {
            if (index >= info.length - 1) {
                throw new CustomExceptions.deadlineExceptionBy("Please use /by command after deadline name");
            }
            this.name += info[index] + " ";
            index++;
        }
        for (int i = index + 1; i < info.length; i++) {
            doneByString += info[i] + " ";
        }
        this.name = this.name.trim();

        try {
            if (doneByString.trim().equals("")) {
                this.doneBy = LocalDateTime.now();
            } else {
                this.doneBy = Parser.parseDTString(doneByString.trim());
            }

        } catch (DateTimeParseException e) {
            throw new CustomExceptions.unrecognizableDateException("Date format is unrecognizable, try dd/mm/yy hhmm");
        }
        this.isDone = false;
        if (this.name.equals("")) {
            throw new CustomExceptions.namelessTaskException("Missing Event Name");
        }
    }

    @Override
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n     " +
                this.toString();
    }

    @Override
    public String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n     " +
                this.toString();
    }

    @Override
    public String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String addMessage(int num) {
        return "Got it. I've added this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    @Override
    public String removeMessage(int num) {
        return "Noted. I've removed this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    @Override
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[D][" + printChecked(this.isDone)+ "] " + this.name + " " + "(by: " +
                this.doneBy.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) +")";
    }
}
