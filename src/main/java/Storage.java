import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
public class Storage {
    private final File storageFile;
    Storage(ArrayList<String> path) throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator + String.join(File.separator, path);
        this.storageFile = new File(filePath);
        if (this.storageFile.getParentFile().mkdirs()) {
            System.out.println("directory created");
        } else {
            System.out.println("directory already exists");
        }
        if (this.storageFile.createNewFile()) {
            System.out.println("new file created");
        } else {
            System.out.println("file already exists");
        }
    }

    public TaskList getTasksFromFile() throws DukeException {
        try {
            TaskList taskList = new TaskList();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.storageFile));
            String line = bufferedReader.readLine();
            while (line != null) {
                taskList.addTask(Task.createTaskFromFileLine(line));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            return taskList;
        } catch (FileNotFoundException err) {
            throw new DukeException("File not found");
        } catch (IOException err) {
            throw new DukeException("Error reading from file");
        }
    }

    public void writeToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.storageFile);
            writer.write(tasks.getFileRepresentation());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error when writing to file: " + e.getMessage());
        }
    }
}
