import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {

    private final String path;

    Storage(String path) {
        this.path = path;
    }

    public void write(TaskList tasks) throws DookException {
        try {
            File f = new File(path);
            if (!f.exists()){
                f.createNewFile();
            }
            FileWriter writer = new FileWriter(path);
            writer.write(tasks.fileRepresentation());
            writer.close();
        } catch (IOException e) {
            throw new DookException("An error occured when writing to your files...:(" + e);
        }
    }

    public TaskList loadTaskListFromFile() throws IOException, DookException {
        TaskList taskList = new TaskList();
        BufferedReader r;
        r = new BufferedReader(new FileReader(path));
        String line = r.readLine();
        while (line != null) {
            taskList.addTask(Parser.parseFileLineToTask(line));
            line = r.readLine();
        }
        r.close();
        return taskList;
    }
}
