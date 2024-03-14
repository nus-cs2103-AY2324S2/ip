package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a subclass of the Task class and represents
 * a task with a from date and a to date
 */
public class Event extends Task {
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructor for an empty Event task
     */
    public Event() {
        super();
    }

    /**
     * Constructor for an Event task
     *
     * @param description Description of Event task
     * @param fromDate Beginning date of Event task as a LocalDate object
     * @param toDate End date of Event task as a LocalDate object
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Constructor for an Event task
     * @param description Description of Event task
     * @param fromDate Beginning date of Event task as a LocalDate object
     * @param toDate End date of Event task as a LocalDate object
     * @param isDone Completion status of the Event task
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate, boolean isDone) {
        super(description, "E", isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public String getFromDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = fromDate.format(formatter);
        return formattedString;
    }

    public LocalDate getToDate() {
        return this.toDate;
    }

    public String getToDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedString = toDate.format(formatter);
        return formattedString;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String formattedFromDate = this.fromDate.format(formatter);
        String formattedToDate = this.toDate.format(formatter);
        String fromTo = "(from: " + formattedFromDate + " to: " + formattedToDate + ")";
        return codeBox + statusBox + " " + description + " " + fromTo;
    }

    /**
     * Converts an Event task from a string to Event object
     *
     * @param saveString Event task in string format
     * @return Event task as an Event object
     */
    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK|dueDate
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        String fromDate = saveDetails[3];
        String toDate = saveDetails[4];
        String[] fromDateStringArray = fromDate.split("-");
        String[] toDateStringArray = toDate.split("-");
        int[] fromDateArray = new int[3];
        int[] toDateArray = new int[3];
        for (int i = 0; i < 3; i++) {
            fromDateArray[i] = Integer.parseInt(fromDateStringArray[i]);
            toDateArray[i] = Integer.parseInt(toDateStringArray[i]);
        }
        LocalDate eventFromDateLocal = LocalDate.of(fromDateArray[0], fromDateArray[1], fromDateArray[2]);
        LocalDate eventToDateLocal = LocalDate.of(toDateArray[0], toDateArray[1], toDateArray[2]);
        return new Event(taskDescription, eventFromDateLocal, eventToDateLocal, isDone);
    }

    /**
     * Converts an Event task from Event object to string (for saving)
     *
     * @return Event task as a string
     */
    public String convertTaskToSave() {
        String taskCode = this.getTaskCode();
        String taskStatus = "0";
        if (this.isDone()) {
            taskStatus = "1";
        }
        String taskDescription = this.getDescription();
        String taskFromDate = this.getFromDateString();
        String taskToDate = this.getToDateString();
        return taskCode + "|" + taskStatus + "|" + taskDescription + "|" + taskFromDate + "|" + taskToDate;
    }
}
