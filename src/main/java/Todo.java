public class Todo extends Task {
    public Todo(String taskDescription) {
        super(taskDescription, TaskType.TODO);
    }

    @Override
    public void setTaskDescription(String taskDescription) {
        // Remove the task type from the description
        String cleanedDescription = taskDescription.replaceFirst("^\\s*todo\\s*", "");
        super.setTaskDescription(cleanedDescription);
    }

}
