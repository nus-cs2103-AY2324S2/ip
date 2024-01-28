import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import tasks.TaskType;
public class TaskFactory {
    public Task createTask(TaskType type, String... args) {
        switch (type) {
            case TODO:
                return new Todo(args[0]);
            case DEADLINE:
                return new Deadline(args);
            case EVENT:
                return new Event(args);
            default:
                throw new IllegalArgumentException("Invalid task type: " + type);
        }
    }
}

