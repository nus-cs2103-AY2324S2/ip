package tasks;

/**
 * The Todo class is a subclass of the Task class and represents
 * a task with no date and time constraint
 */
public class Todo extends Task {

    /**
     * Constructor for an empty Todo task
     */
    public Todo() {
        super();
    }

    /**
     * Constructor for a Todo task
     *
     * @param description Description of Todo task
     */
    public Todo(String description) {
        super(description, "T");
    }

    /**
     * Constructor for a Todo task
     *
     * @param description Description of Todo task
     * @param isDone Completion status of the Todo task
     */
    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        return codeBox + statusBox + " " + description;
    }

    /**
     * Converts a Todo task from a string to a Todo object
     *
     * @param saveString Todo task in string format
     * @return Todo task as a Todo object
     */
    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        return new Todo(taskDescription, isDone);
    }

    /**
     * Converts a Todo task from Todo object to string (for saving)
     *
     * @return Todo task as a string
     */
    public String convertTaskToSave() {
        String taskCode = this.getTaskCode();
        String taskStatus = "0";
        if (this.isDone()) {
            taskStatus = "1";
        }
        String taskDescription = this.getDescription();
        return taskCode + "|" + taskStatus + "|" + taskDescription;
    }
}
