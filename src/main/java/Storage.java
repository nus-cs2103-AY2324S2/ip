import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from data file.
     *
     * @return A taskList stored in data file.
     */
    public ArrayList<Task> loadData() throws FileNotFoundException, WrongTaskDataException{
        ArrayList<Task> tasks = new ArrayList<>(100);

        // Scans stored data.
        File data = new File(this.filePath);
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            tasks.add(getTasksFromData(s.nextLine()));
        }
        s.close();
        return tasks;
    }

    /**
     * Fetches tasks stored in hard disk.
     *
     * @param task String representation of task stored.
     * @return A Task in data file.
     * @throws WrongTaskDataException Alerts users when wrong data detected.
     */
    public Task getTasksFromData(String task) throws WrongTaskDataException {
        String[] temp = task.split(" \\| ", 5);
        switch (temp[0]) {
        case "T":
            if (temp.length != 3) {
                throw new WrongTaskDataException();
            }
            Task t1 = new ToDo(temp[2]);
            if (temp[1].equals("1")) {
                t1.mark();
            }
            return t1;
        case "D":
            if (temp.length != 4) {
                throw new WrongTaskDataException();
            }
            Task t2 = new Deadline(
                    temp[2],
                    LocalDateTime.parse(temp[3]));
            if (temp[1].equals("1")) {
                t2.mark();
            }
            return t2;
        case "E":
            if (temp.length != 5) {
                throw new WrongTaskDataException();
            }
            Task t3 = new Event(
                    temp[2],
                    LocalDateTime.parse(temp[3]),
                    LocalDateTime.parse(temp[4]));
            if (temp[1].equals("1")) {
                t3.mark();
            }
            return t3;
        default:
            throw new WrongTaskDataException();
        }
    }

    /**
     * Writes updated data back to data file.
     *
     * @param newData Updated data to be stored before program is closed.
     * @throws IOException Alerts users that data file cannot be written.
     */
    public void writeBackData(String newData) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.append(newData);
        fileWriter.close();
    }
}
