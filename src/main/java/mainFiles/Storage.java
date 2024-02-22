package mainfiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Class used to read and update files from the hard disk
 */
public class Storage {

    public Storage() {
    }
    /**
     * Reads the file that is specified in newFile and stores it in the taskList.
     * @param newFile File to be read
     * @param taskList ArrayList that contains all the tasks
     * @throws FileNotFoundException If the specified file does not exist
     */
    public void readFile(File newFile, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(newFile);
        while (s.hasNextLine()) {
            String readLine = s.nextLine();
            String eventType = readLine.split(" \\| ")[0];
            boolean cleared = readLine.split(" \\| ")[1].equals("1");
            String description = readLine.split(" \\| ", 3)[2];
            Task t;
            switch (eventType) {
            case "T":
                t = new Todo(description);
                break;
            case "D":
                t = new Deadline(description.split(" \\| ")[0], LocalDate.parse(description.split(" \\| ")[1]));
                break;
            case "E":
                t = new Event(description.split(" \\| ")[0], LocalDate.parse(description.split(" \\| ")[1]),
                        LocalDate.parse(description.split(" \\| ")[2]));
                break;
            default:
                t = new Task("errorTask");
            }
            if (cleared) {
                t.toggleCompletion();
            }
            taskList.add(t);
        }
    }

    /**
     * Refreshes the file in ./data/Steven.txt such that it is in line with that of the ArrayList/
     * @param list The ArrayList that stores the list of tasks.
     * @throws IOException If the ./data/Steven.txt cannot be accessed.
     */
    public void refreshFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter("./data/Steven.txt");
        for (Task t : list) {
            String description = t.storeFormat() + "\n";
            fw.write(description);
        }
        fw.close();
    }
}
