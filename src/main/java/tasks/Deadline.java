package tasks;

import cro.*;

import java.time.LocalDateTime;
import java.util.List;

public class Deadline extends Task {

    private LocalDateTime deadline;
    public Deadline(List<String> splitStr) throws CroException{
        int isDone = Integer.parseInt(splitStr.get(1));
        int byIndex = splitStr.indexOf("/by");
        String description = String.join(" ", splitStr.subList(2, byIndex));
        try {
            this.deadline = LocalDateTime.parse(splitStr.get(byIndex + 1));
        } catch (Exception e) {
            throw new CroException("deadline must be in the format YYYY MM DD HH MM");
        }
        if (description.equals("")) {
            throw new CroException("description or deadline cannot be empty!");
        }
        this.description = description;
        if (isDone == 1) {
            this.markAsDone();
        }
    }
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), this.description, this.deadline.format(dateFormat));
    }
    @Override
    public String getSaveLine() {
        return "D " + (this.isDone ? "1 " : "0 ") + this.description + " /by " + this.deadline.toString() + "\n";
    }
}
