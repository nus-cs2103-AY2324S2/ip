package duke.mainUtils;

import duke.exceptions.InvalidDateException;
import duke.exceptions.StorageException;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.*;

public class Storage {
    private final String filePath;

    private final TaskList taskList;

    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    public void load() throws StorageException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    taskList.addTask(Parser.parseSaveFile(line));
                }
            }
        } catch (IOException e) {
            createSaveFile();
        } catch (InvalidDateException ex) {
            throw new StorageException();
        }
    }

    public void save() throws StorageException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : this.taskList) {
                writer.println(task.toString());
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }

    private void createSaveFile() throws StorageException {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            }
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            }
        } catch (IOException e) {
            throw new StorageException();
        }
    }


}
