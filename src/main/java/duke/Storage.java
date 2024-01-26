package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Represents the disk storage for the task list.
 */
public class Storage {
    private TaskList taskList;
    private File file;

    /**
     * Creates a new Storage object.
     * 
     * @param filePath The path to the file that stores the task list.
     * @throws DukeException If the file does not exist and cannot be created.
     */
    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating new file");
            }
        }
    }

    /**
     * Saves the task list to the file.
     * Assumption: load should be called before save.
     * 
     * @throws DukeException If there is an error writing to file.
     */
    public void save() throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < this.taskList.getSize(); i++) {
                Task task = this.taskList.getTask(i);
                String taskString = task.toFileLine();
                fileWriter.write(taskString + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file");
        }
    }

    /**
     * Loads the task list from the file.
     * 
     * @return The task list.
     * @throws DukeException If there is an error reading from file.
     */
    public TaskList load() throws DukeException {
        this.taskList = new TaskList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();
            while (line != null) {
                Task task = TaskBuilder.createTaskFromFileLine(line);
                this.taskList.addTask(task);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error loading tasks from file");
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file");
        }
        return this.taskList;
    }
}
