package aaron.task;

import aaron.exception.InvalidTaskTypeException;

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

    /**
     * Method to get task type from a string
     * 
     * @param taskType String
     * @return task type that corresponds to string
     * @throws InvalidTaskTypeException if unrecognized task type
     */
    public static TaskType getTaskType(String taskType) throws InvalidTaskTypeException {
        for (TaskType task : TaskType.values()) {
            if (taskType.equalsIgnoreCase(task.name())) {
                return task;
            }
        }
        throw new InvalidTaskTypeException("unknown task type: " + taskType);
    }
}