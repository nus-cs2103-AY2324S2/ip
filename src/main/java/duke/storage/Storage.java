package duke.storage;

import duke.common.Messages;
import duke.common.TaskList;
import duke.exception.StorageOperationException;
import duke.task.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final String FILE_PATH = "duke.txt";

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

    public List<Task> load() throws StorageOperationException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            List<Task> taskList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                taskList.add(TaskDecoder.decodeTask(line));
            }
            return taskList;
        } catch (IOException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FAILED_STORAGE, "File may be corrupted"));
        }
    }
}
