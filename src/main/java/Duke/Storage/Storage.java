package Duke.Storage;

import Duke.DukeException.DukeException;
import Duke.Task.Deadlines.Deadlines;
import Duke.Task.Events.Events;
import Duke.Task.Task;
import Duke.Task.ToDos.ToDos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class saves and loads the text file for the added task.
 * @author Tang Hao Liang
 */
public class Storage {

    private static File file;

    /**
     * Constructor to create a new File class with the path to file.
     *
     * @param filePath Path to file
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Returns ArrayList of tasks from file.
     *
     * @return ArrayList of tasks.
     * @throws IOException If file is corrupted.
     */
    public ArrayList<Task> load() throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner s = new Scanner(file);
        int count = 0;
        while (s.hasNext()) {
            String str = s.nextLine();
            String[] split = str.split("[|]");

            if (split[0].equalsIgnoreCase("T")) {
                taskList.add(new ToDos(split[2]));
            } else if (split[0].equalsIgnoreCase("D")) {
                taskList.add(new Deadlines(split[2], LocalDate.parse(split[3].strip())));
            } else if (split[0].equalsIgnoreCase("E")) {
                taskList.add(new Events(split[2],split[3],split[4]));
            }
            if (Boolean.parseBoolean(split[0])) {
                taskList.get(count).markAsDone();
            }
        }
        return taskList;
    }

    private void appendToFile(String str) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(str + "\n");
        fw.close();
    }

    /**
     * Saves current tasks list to file.
     *
     * @param taskList Current task list
     * @throws DukeException If file is corrupted.
     */
    public void arrayToFile(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();

            for (Task task : taskList) {
                appendToFile(task.toFile());
            }
        } catch (IOException e) {
            throw new DukeException("Error when writing to file\n");
        }
    }
}
