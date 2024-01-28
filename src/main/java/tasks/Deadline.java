package tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;


    public Deadline(String[] parts) {
        super(parts[0]);
        this.type = TaskType.DEADLINE;
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid input for deadline task. Expected: <description> /by <dueDate>");
        }
        try {
            String due = parts[1].substring(3).trim();
            this.dueDate = Parser.parseDate(due);
        } catch (Exception e) {
            System.out.println("Error parsing LocalDateTime: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    @Override
    public String formattedString() {

        return "D" + super.formattedString() + "|" + dueDate.toString();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}