package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate dueByDate;

    public Deadline(String description, LocalDate dueByDate) {
        super(description);
        this.dueByDate = dueByDate;
    }

    public static void addDeadlineTask(TaskList taskList, String description, String dueBy) throws DukeException{
        LocalDate dueByDate = parseDate(dueBy);
        taskList.addTask(new Deadline(description, dueByDate));
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.getTask(taskList.size() - 1).getStatusIcon());
        System.out.println("Now you have " + taskList.size() + " task" + (taskList.size() == 1 ? "" : "s") + " in the list.");
    }

    @Override
    public String getStatusIcon() {
        return "[D] " + super.getStatusIcon() + " " + description + " (by: " +
                dueByDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        if (dueByDate == null) {
            return "";
        }

        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " +
                dueByDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}