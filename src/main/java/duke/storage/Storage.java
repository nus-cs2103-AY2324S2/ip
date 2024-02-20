package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Storage class to manage saving of files
 */
public class Storage {

    /** file path to store txt file */
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads list of tasks from existing txt file
     * else creates a new blank txt file
     * @return ArrayList List of existing tasks (if any)
     */
    public ArrayList<Task> load() {
        ArrayList<Task> store = new ArrayList<Task>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                Task nextTask = Parser.parseFromStorage(s.nextLine());
                store.add(nextTask);
            }
        } catch (FileNotFoundException e) {
            try {
                File f = new File(filePath);
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return store;
    }

    /**
     * Saves list of tasks into txt file
     *
     * @param store TaskList to be stored into txt file
     */
    public void saveTasks(TaskList store) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String toSave = "";
        for (Task task : store.getTasks()) {
            toSave = toSave + task.getType() + ";;;"
                    + (task.isCompleted() ? 1 : 0) + ";;;"
                    + task.getDetails() + "\n";
        }
        fw.write(toSave);
        fw.close();
    }


}
