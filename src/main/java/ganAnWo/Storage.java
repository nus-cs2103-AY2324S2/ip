package ganAnWo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import ganAnWo.task.Deadline;
import ganAnWo.task.Event;
import ganAnWo.task.Task;
import ganAnWo.task.ToDos;


/**
 * Class used to do all methods related to save & load.
 *
 *
 */
public class Storage {
    private static final DateTimeFormatter DATE_FORMAT_INP = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private String path;
    private File data;

    /**
     * Constructor for storage
     *
     * @param filePath path of file in string.
     */
    public Storage(String filePath) {
        path = filePath;
        data = new File(path);
    }

    /**
     * Returns an ArrayList of task from the saved file.
     *
     * @return an ArrayList contains Task.
     * @throws FileNotFoundException If file is not yet made.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskF = new ArrayList<>();
        if (!data.exists()) {
            throw new FileNotFoundException("No saved data before, making new one");
        }
        Scanner sc = new Scanner(data);
        while (sc.hasNext()) {
            String dt = sc.nextLine();
            String[] dtl = dt.split("/");
            switch (dtl[0]) {
            case "T":
                taskF.add(new ToDos(dtl[1], dtl[2]));
                break;
            case "D":
                taskF.add(new Deadline(dtl[1], dtl[2], LocalDateTime.parse(dtl[3], DATE_FORMAT_INP)));
                break;
            case "E":
                taskF.add(new Event(dtl[1], dtl[2], LocalDateTime.parse(dtl[3], DATE_FORMAT_INP),
                        LocalDateTime.parse(dtl[4], DATE_FORMAT_INP)));
                break;
            default:
            }
        }
        return taskF;
    }

    /**
     * Saves the given ArrayList to the file.
     *
     * @param tasks an ArrayList of task.
     * @throws IOException If save failed.
     */
    public void write(ArrayList<Task> tasks) throws IOException {
        FileWriter rFile;
        rFile = new FileWriter(path);
        for (int i = 0; i < tasks.size(); i++) {
            rFile.write(tasks.get(i).toWrite());
            rFile.write("\n");
        }
        rFile.close();
    }
}
