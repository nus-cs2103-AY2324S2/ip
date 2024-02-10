package earl.util.parsers;

import java.util.Arrays;

import earl.exceptions.EarlException;
import earl.tasks.Task;
import earl.tasks.TaskType;

/**
 * Class responsible for interpreting storage entries.
 */
public class StorageParser implements Parser {

    /**
     * Returns a new {@code Task} object based on stored entry.
     *
     * @param entry           a line of text stored in the data file
     * @return                a {@code Task} object of the relevant type
     * @throws EarlException  if stored entry is of unexpected format
     */
    public static Task parse(String entry) throws EarlException {
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
        } catch (IllegalArgumentException e) {
            throw new EarlException("Storage file is corrupted... "
                    + "starting with empty list.");
        } catch (Exception e) {
            throw new EarlException("Unknown exception occurred "
                    + "when attempting to parse storage file: "
                    + e.getMessage());
        }
    }
}
