public class Todo extends Task {
    public Todo() {
        super();
    }
    public Todo(String description) {
        super(description, "T");
    }

    public Todo(String description, boolean isDone) {
        super(description, "T", isDone);
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        return codeBox + statusBox + " " + description;
    }

    public Task convertSaveToTask(String saveString) {
        //T|1|READ BOOK
        String[] saveDetails = saveString.split("\\|");
        int doneStatus = Integer.parseInt(saveDetails[1]);
        boolean isDone = doneStatus == 1;
        String taskDescription = saveDetails[2];
        return new Todo(taskDescription, isDone);
    }

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
