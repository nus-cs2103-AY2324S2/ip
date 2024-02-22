package sky;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Represents a local storage to save and load tasks from a file.
 */
public class Storage {
    private static final String filePath = Paths.get("data", "sky.txt").toString();
    private File dataFile = new File(filePath);

    /**
     * Constructor for Storage.
     */
    public void createDataFile() throws Exception {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        this.dataFile.createNewFile();
    }

    /**
     * Loads tasks from the data file into the task list.
     * @param list Task list to load the tasks into.
     */
    public void loadData(TaskList list) throws Exception {
        if (!this.dataFile.exists()) {
            createDataFile();
            return;
        } else {
            Scanner sc = new Scanner(this.dataFile);
            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(" \\| ");
                String type = input[0];
                boolean isDone = input[1].equals("1");
                String description = input[2];
                switch (type) {
                case "T":
                    list.add(new Todo(description, isDone));
                    break;
                case "D":
                    list.add(new Deadline(description, input[3], isDone));
                    break;
                case "E":
                    list.add(new Event(description, input[3], input[4], isDone));
                    break;
                default:
                    break;
                }
            }
            sc.close();
        }
    }

    /**
     * Saves tasks from the task list into the data file.
     * @param list Task list to save the tasks from.
     * @param dest Destination to save the tasks to.
     */
    public void saveData(TaskList list, String... dest) throws Exception {
        File destFile = this.dataFile;
        if (dest.length > 0) {
            destFile = new File(Paths.get("data", dest[0]).toString());
        }
        java.io.FileWriter fw = new java.io.FileWriter(destFile);
        for (Task task : list.getList()) {
            String type = "";
            String description = task.getDescription();
            String isDone = task.getIsDone() ? "1" : "0";
            String by = "";
            String start = "";
            String end = "";
            if (task instanceof Todo) {
                type = "T";
                fw.write(type + " | " + isDone + " | " + description + "\n");
            } else if (task instanceof Deadline) {
                type = "D";
                by = ((Deadline) task).getBy();
                fw.write(type + " | " + isDone + " | " + description + " | " + by + "\n");
            } else if (task instanceof Event) {
                type = "E";
                start = ((Event) task).getStart();
                end = ((Event) task).getEnd();
                fw.write(type + " | " + isDone + " | " + description + " | " + start + " | " + end + "\n");
            }
        }
        fw.close();
    }
}
