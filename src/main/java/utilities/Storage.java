package utilities;

import task.TaskList;
import task.Task;

import java.util.ArrayList;

public class Storage {
    private SaveFile saveFile;
    public Storage(String fileName, String directoryName) {
        this.saveFile = new SaveFile(fileName, directoryName);
        this.saveFile.createDirectory();
        this.saveFile.createFile();
    }

    public void save(TaskList taskList) {
        this.saveFile.saveToFile(taskList);
    }

    public ArrayList<Task> load() {
        return this.saveFile.readFile();
    }
}
