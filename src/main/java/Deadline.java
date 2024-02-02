public class Deadline extends Task {
    String dueDate = "";

    public Deadline() {
        super();
    }

    public Deadline(String description, String dueDate) {
        super(description, "D");
        this.dueDate = dueDate;
    }

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, "D", isDone);
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        String due = "(by: " + this.dueDate + ")";
        return codeBox + statusBox + " " + description + " " + due;
    }

    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK|startdate|enddate
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        String dueDate = saveDetails[3];
        return new Deadline(taskDescription,dueDate, isDone);
    }

    public String convertTaskToSave() {
        String taskCode = this.getTaskCode();
        String taskStatus = "0";
        if (this.isDone()) {
            taskStatus = "1";
        }
        String taskDescription = this.getDescription();
        String taskDueDate = this.getDueDate();
        return taskCode + "|" + taskStatus + "|" + taskDescription + "|" + taskDueDate;
    }
}
