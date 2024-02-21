package seedu.duke.storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import seedu.duke.common.Messages;
import seedu.duke.common.TaskList;
import seedu.duke.exception.StorageOperationException;
import seedu.duke.task.Task;


/**
 * The Storage class implements methods to save the TaskList created by the users to the given file and
 * load the tasks from the file
 */
public class Storage {
    public static final String FILE_PATH = "data.txt";

    /**
     * Saves the tasks created by the users to a given file by encoding them with pre-defined format
     *
     * @param taskList the tasks to be saved
     * @throws StorageOperationException if there is exception occurs when writing to file
     */
    public void save(TaskList taskList) throws StorageOperationException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskList.getListSize(); i++) {
                String taskString = TaskEncoder.encodeTask(taskList.getTask(i));
                fileWriter.write(taskString);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FAILED_STORAGE, "failed to save tasks."));
        }
    }

    /**
     * Returns tasks that loaded from the given file
     *
     * @return The tasks loaded
     * @throws StorageOperationException if there is exception writing into file or parsing the formats into task
     */
    public List<Task> load() throws StorageOperationException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            List<Task> taskList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(TaskDecoder.decodeTask(line));
            }
            return taskList;
        } catch (FileNotFoundException ignored) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FAILED_STORAGE, "File may be corrupted"));
        }
    }
}
