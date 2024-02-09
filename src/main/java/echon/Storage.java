package echon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
     * @throws EchonException If the file does not exist and cannot be created.
     */
    public Storage(String filePath) throws EchonException {
        this.file = new File(filePath);
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                throw new EchonException("Error creating new file");
            }
        }
        assert this.file.exists() : "File should exist";
    }

    /**
     * Saves the task list to the file.
     * Assumption: load should be called before save.
     *
     * @throws EchonException If there is an error writing to file.
     */
    public void save() throws EchonException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < this.taskList.getSize(); i++) {
                Task task = this.taskList.getTask(i);
                String taskString = task.toFileLine();
                fileWriter.write(taskString + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new EchonException("Error saving tasks to file");
        }
    }

    /**
     * Loads the task list from the file.
     *
     * @return The task list.
     * @throws EchonException If there is an error reading from file.
     */
    public TaskList load() throws EchonException {
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
            throw new EchonException("Error loading tasks from file");
        } catch (IOException e) {
            throw new EchonException("Error loading tasks from file");
        }
        return this.taskList;
    }
}
