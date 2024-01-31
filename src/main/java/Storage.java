import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filepath;
    private File file;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public List<Task> load() throws ToothlessException{
        try {
            List<Task> list = new ArrayList<>();
            this.file = new File(this.filepath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task;
                String[] storedTask = sc.nextLine().split(" \\| ");
                switch (storedTask[0]){
                    case "T":
                        task = new Todo(storedTask[2], storedTask[1].equals("1"));
                        break;
                    case "D":
                        task = new Deadline(storedTask[2], storedTask[3], storedTask[1].equals("1"));
                        break;
                    case "E":
                        task = new Event(storedTask[2], storedTask[3], storedTask[4], storedTask[1].equals("1"));
                        break;
                    default:
                        throw new ToothlessException("File corrupted O_O. Try again later.");
                }
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException exception) {
            new File(this.filepath).getParentFile().mkdirs();
            return new ArrayList<>();
        }
    }

    public void writeTasks(TaskList tasks){
        try {
            FileWriter writer = new FileWriter(this.filepath);
            for (int i = 0; i < tasks.size(); i++){
                writer.write(tasks.getTask(i).toWrite() + "\n");
            }
            writer.close();
        } catch (IOException e){
            System.err.println("Unable to save task :(");
        }
    }
}
