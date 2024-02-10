public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private final String type;

    TaskType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static TaskType getTaskType(String taskType) throws InvalidTaskTypeException {
        for (TaskType task : TaskType.values()) {
            if (taskType.equalsIgnoreCase(task.name())) {
                return task;
            }
        }
        throw new InvalidTaskTypeException("unknown task type: " + taskType);
    }
}