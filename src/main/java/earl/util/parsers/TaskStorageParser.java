package earl.util.parsers;

import java.util.Arrays;

import earl.exceptions.ParserException;
import earl.tasks.Task;
import earl.tasks.TaskType;

/**
 * Class responsible for parsing stored entries of tasks.
 */
public class TaskStorageParser implements Parser<Task> {

    /**
     * Returns a {@code Task} object based on the stored string.
     *
     * @param entry             storage entry string
     * @return                  a {@code Task} object
     * @throws ParserException  if the storage entry is incomprehensible
     */
    public static Task parse(String entry) throws ParserException {
        try {
            String[] data = entry.split(",");
            String type = data[0];
            String status = data[1];
            String[] args = Arrays.copyOfRange(data, 2, data.length);
            TaskType taskType = TaskType.valueOf(type);
            Task task = taskType.createTask(args);
            if (status.equals("X")) {
                task.markAsDone();
            }
            return task;
        } catch (Exception e) {
            throw new ParserException();
        }
    }
}
