package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private TaskList taskList;
    private File file;

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
