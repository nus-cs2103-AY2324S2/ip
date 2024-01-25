public class Deadline extends Task{
    private String taskDeadline;
    public Deadline(String taskDescription) {
        super(taskDescription, TaskType.DEADLINE);
        parseDeadline(taskDescription);
    }

    private String parseDeadline(String taskDescription) {
        String[] parts = taskDescription.split("/by", 2);

        if (parts.length == 2) {
            taskDeadline = parts[1].trim();
            return parts[0].trim() + " (by: " + taskDeadline + ")";
        } else {
            throw new IllegalArgumentException("Invalid task description for deadline");
        }
    }

    public String getDueTime() {
        return taskDeadline;
    }

    @Override
    public void setTaskDescription(String taskDescription) {
        super.setTaskDescription(parseDeadline(taskDescription));
    }
}
