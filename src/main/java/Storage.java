import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File filePath;
    public Storage(String path) {
        filePath = new File(path);
    }

    public ArrayList<Task> load() throws DukeNoSaveFile {
        ArrayList<Task> saveFile = new ArrayList<>();
        try {
            Scanner s = new Scanner(filePath);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] inputs = line.split(" \\| ");
                boolean isDone = !inputs[1].equals("0");
                Task task;
                switch (inputs[0]) {
                case "T":
                    task = new ToDos(inputs[2], isDone);
                    saveFile.add(task);
                    break;
                case "D":
                    task = new Deadlines(inputs[2], LocalDateTime.parse(inputs[3]), isDone);
                    saveFile.add(task);
                    break;
                case "E":
                    String[] time = inputs[3].split("/");
                    task = new Events(inputs[2], LocalDateTime.parse(time[0])
                            , LocalDateTime.parse(time[1]), isDone);
                    saveFile.add(task);
                    break;
                }
            }
            return saveFile;
        } catch (FileNotFoundException e) {
            throw new DukeNoSaveFile();
        }
    }

    public void save(TaskList taskList) throws DukeSaveError{
        try {
            filePath.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.saveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeSaveError();
        }
    }
}
