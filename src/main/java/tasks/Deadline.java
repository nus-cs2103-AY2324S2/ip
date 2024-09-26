package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a subclass of the Task class and represents
 * a task with a due date
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructor for an empty Deadline task
     */
    public Deadline() {
        super();
    }

    /**
     * Constructor for a Deadline task
     *
     * @param description Description of Deadline task
     * @param dueDate Due date of Deadline task as a LocalDate object
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description, "D");
        this.dueDate = dueDate;
    }

    /**
     * Constructor for a Deadline task
     *
     * @param description Decription of Deadline task
     * @param dueDate Due date of Deadline task as a LocalDate object
     * @param isDone Completion status of the Deadline task
     */
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

    /**
     * Converts a Deadline task from a string to Deadline object
     *
     * @param saveString Deadline task in string format
     * @return Deadline task as a Deadline object
     */
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

    /**
     * Converts a Deadline task from Deadline object to string (for saving)
     *
     * @return Deadline task as a string
     */
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
