import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveFile {
    private File saveFile;
    private String path;

    public SaveFile(String path) throws DukeException {
        this.saveFile = new File(path);
        this.path = path;
        if (!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("ERROR! IOException occurred!");
            }
        }
    }

    public SaveFile() {
        this("./data/duke.txt");
    }

    public TaskList getTasks() throws DukeException {
        try {
            Scanner fileScanner = new Scanner(this.saveFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("ERROR! File not found!");
        }
        TaskList taskList = new TaskList();
        while (fileScanner.hasNextLine()) {
            String curLine = fileScanner.nextLine();
            taskList.addTask(Task.generateTaskFromFile(curLine));
        }
        return taskList;
    }

    public void saveTasks(TaskList taskList) {

    }
}
