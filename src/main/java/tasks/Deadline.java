package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate dueDate;

    public Deadline() {
        super();
    }

    public Deadline(String description, LocalDate dueDate) {
        super(description, "D");
        this.dueDate = dueDate;
    }

    public Deadline(String description, LocalDate dueDate, boolean isDone) {
        super(description, "D", isDone);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public String getDueDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = dueDate.format(formatter);
        return formattedString;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedDueDate = dueDate.format(formatter);
        String due = "(by: " + formattedDueDate + ")";
        return codeBox + statusBox + " " + description + " " + due;
    }

    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK|dueDate
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        String[] dueDateStringArray = saveDetails[3].split("-");
        int[] dueDateArray = new int[3];
        for (int i = 0; i < 3; i++) {
            dueDateArray[i] = Integer.parseInt(dueDateStringArray[i]);
        }
        LocalDate dueDate = LocalDate.of(dueDateArray[0], dueDateArray[1], dueDateArray[2]);
        return new Deadline(taskDescription, dueDate, isDone);
    }

    public String convertTaskToSave() {
        String taskCode = this.getTaskCode();
        String taskStatus = "0";
        if (this.isDone()) {
            taskStatus = "1";
        }
        String taskDescription = this.getDescription();
        String taskDueDate = this.getDueDateString();
        return taskCode + "|" + taskStatus + "|" + taskDescription + "|" + taskDueDate;
    }
}
