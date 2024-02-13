package zoe;

import java.io.*;
import java.util.ArrayList;

public class Loader {
    protected String path;
    protected String fileName;

    public Loader(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    /**
     * Loads data from a saved file when zoe is opened again
     * If no file exists, creates an empty Array list for zoe.
     */
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
                    int taskType = LoadSplit.Task_Type.getIdx();
                    int description = LoadSplit.Description.getIdx();
                    int status = LoadSplit.Status.getIdx();

                    if (readTask[taskType].equals("todo")) {
                        ToDo td = new ToDo(
                                readTask[description], readTask[status]);
                        loadedTasks.add(td);

                    } else if (readTask[taskType].equals("event")) {
                        Event e = new Event(readTask[description], readTask[status]);
                        loadedTasks.add(e);

                    } else {
                        Deadline dl = new Deadline(readTask[description], readTask[status]);
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
