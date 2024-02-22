package destiny;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Used to load/store TaskList from/to a destiny.txt data file.
 */
public class Storage {

    private final String dataPath = System.getProperty("user.dir") + "/data";
    private final String fileName = "destiny.txt";

    /**
     * Attempts to load data from a destiny.txt file that would contain the previously saved TaskList.
     * Creates a new .txt file if it is not found.
     *
     * @return ArrayList of tasks that will be loaded into the Tasklist.
     */
    public ArrayList<Task> loadData() {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        ArrayList<Task> tempTaskStorage = new ArrayList<>(100);

        // check if data directory and file exist already
        if (!directory.exists()) {
            directory.mkdir();
            try {
                // first initialization, create new save file
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!file.exists()) {
            try {
                // data file missing, create new save file
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Scanner s = new Scanner(file);
                while (s.hasNextLine()) {
                    String newEntry = s.nextLine();
                    String[] entryDetails = newEntry.split(" \\| ");
                    Task newTask;
                    switch (entryDetails[0]) {
                    case "T":
                        newTask = new ToDo(entryDetails[1], entryDetails[2]);
                        tempTaskStorage.add(newTask);
                        break;
                    case "D":
                        newTask = new Deadline(entryDetails[1], entryDetails[2], entryDetails[3]);
                        tempTaskStorage.add(newTask);
                        break;
                    case "E":
                        newTask = new Event(entryDetails[1], entryDetails[2], entryDetails[3], entryDetails[4]);
                        tempTaskStorage.add(newTask);
                        break;
                    default:
                        // ignores corrupted line in data file
                    }
                }
                return tempTaskStorage;
            } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                try {
                    file.delete();
                    // data file corrupted, create new save file
                    file.createNewFile();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(100);
    }

    /**
     * Saves the TaskList given into the destiny.txt data file.
     *
     * @param tasks The TaskList containing all tasks currently saved by Destiny.
     */
    public void saveData(TaskList tasks) {
        File directory = new File(dataPath);
        File file = new File(dataPath + "/" + fileName);
        // reset file
        try {
            file.delete();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            ArrayList<Task> taskList = tasks.getTaskList();
            for (Task tsk: taskList) {
                if (tsk instanceof ToDo) {
                    writer.write("T | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + System.lineSeparator());
                } else if (tsk instanceof Deadline) {
                    writer.write("D | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Deadline) tsk).getBy()
                            + System.lineSeparator());
                } else if (tsk instanceof Event) {
                    writer.write("E | " + tsk.getStatusAsNum() + " | " + tsk.getDescription()
                            + " | " + ((Event) tsk).getFrom() + " | " + ((Event) tsk).getTo()
                            + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
