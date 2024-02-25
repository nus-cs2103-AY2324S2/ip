package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.task.Task;
import duke.task.deadlines.Deadlines;
import duke.task.events.Events;
import duke.task.todos.ToDos;

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
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            if (split[0].equalsIgnoreCase("T")) {
                taskList.add(new ToDos(split[2]));
            } else if (split[0].equalsIgnoreCase("D")) {
                LocalDateTime by = LocalDateTime.parse(split[3].strip(), dateTimeFormat);
                taskList.add(new Deadlines(split[2], by));
            } else if (split[0].equalsIgnoreCase("E")) {

                LocalDateTime from = LocalDateTime.parse(split[3], dateTimeFormat);
                LocalDateTime to = LocalDateTime.parse(split[4], dateTimeFormat);
                taskList.add(new Events(split[2], from, to));
            }

            if (split[1].equals("1")) {
                taskList.get(count).markAsDone();
            }
            count++;
        }
        return taskList;
    }

    /**
     * Saves current tasks list to file.
     *
     * @param taskList Current task list
     * @throws DukeException If file is corrupted.
     */
    public void saveToFile(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write("");
            fw.close();

            fw = new FileWriter(file, true);
            for (Task task : taskList) {
                fw.write(task.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error when writing to file\n");
        }
    }
}
