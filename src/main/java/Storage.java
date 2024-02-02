import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public Storage() {
        this("./duke.txt");
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] task = line.split("," );
                Task t = null;
                switch (task[0]) {
                    case "T":
                        t = new Todo(task[2]);
                        break;
                    case "D":
                        t = new Deadline(task[2], task[3]);
                        break;
                    case "E":
                        t = new Event(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
                    t.mark();
                }
                tasks.add(t);
            }
            fileScanner.close();
        } catch (IOException e) {
            throw new DukeException("Unable to load file");
        }
        return tasks;
    }

    public void save(TaskList tasklist) throws DukeException {
        try {
            ArrayList<Task> tasks = tasklist.getTasks();
            File file = new File(this.filepath);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("created file");
            }
            PrintWriter writer = new PrintWriter(file);
            for (Task t: tasks) {
                writer.println(t.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save file");
        }
    }
}
