import java.io.*;
import java.util.ArrayList;
public class Loader {
    protected String path;
    protected String fileName;

    public Loader(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    public ArrayList<Task> loadTasks(){
        File file = new File(path, fileName);
        try {
            File directory = file.getParentFile();
            //check for existence of directory
            if (directory.exists() && file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                ArrayList<Task> loadedTasks = new ArrayList<Task>();
                String line;
                while (!(line = reader.readLine()).equals("EOF")) {
                    String[] readTask = line.split("_");
                    if (readTask[0].equals("todo")) {
                        ToDo td = new ToDo(readTask[1], readTask[2]);
                        loadedTasks.add(td);
                    } else if (readTask[0].equals("event")) {
                        Event e = new Event(readTask[1], readTask[2]);
                        loadedTasks.add(e);
                    } else {
                        Deadline dl = new Deadline(readTask[1], readTask[2]);
                        loadedTasks.add(dl);
                    }
                }
                return loadedTasks;
            } else {
                return new ArrayList<Task>();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<Task>();
    }
}
