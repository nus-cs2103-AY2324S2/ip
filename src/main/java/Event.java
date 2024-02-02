public class Event extends Task {
    String fromDate = "";
    String toDate = "";

    public Event() {
        super();
    }

    public Event(String description, String fromDate, String toDate) {
        super(description, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String description, String fromDate, String toDate, boolean isDone) {
        super(description, "E", isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        String fromTo = "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return codeBox + statusBox + " " + description + " " + fromTo;
    }

    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK|dueDate
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        String fromDate = saveDetails[3];
        String toDate = saveDetails[4];
        return new Event(taskDescription,fromDate, toDate, isDone);
    }

    public String convertTaskToSave() {
        String taskCode = this.getTaskCode();
        String taskStatus = "0";
        if (this.isDone()) {
            taskStatus = "1";
        }
        String taskDescription = this.getDescription();
        String taskFromDate = this.getFromDate();
        String taskToDate = this.getToDate();
        return taskCode + "|" + taskStatus + "|" + taskDescription + "|" + taskFromDate + "|" + taskToDate;
    }
}
