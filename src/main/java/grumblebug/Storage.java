package grumblebug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class is deprecated since GUI is introduced.
 * Class represents the object used to deal with storage into and out of the
 * local file.
 */
public class Storage {
    private String filePath = "./tasks.txt";

    /**
     * Load any previously stored task data into the current taskList.
     * 
     * @param filePath The path to the storage file.
     * @param taskList The taskList to store this data into.
     */
    public void loadFromFile(TaskList taskList) {
        Parser parserForStorage = new Parser("yyyy-MM-dd");
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String task = s.nextLine();
                boolean done = s.nextLine() == "true" ? true : false;
                String desc = s.nextLine();
                Task t;
                if (task.equals("T")) {
                    t = new Task(done, desc);
                } else if (task.equals("D")) {
                    LocalDate deadline = parserForStorage.parseDate(s.nextLine());
                    t = new Task(done, desc, deadline);
                } else if (task.equals("E")) {
                    LocalDate startDate = parserForStorage.parseDate(s.nextLine());
                    LocalDate endDate = parserForStorage.parseDate(s.nextLine());
                    t = new Task(done, desc, startDate, endDate);
                } else {
                    t = null;
                    System.out.println("Bad formatting in tasks.txt...");
                    assert false; // If we enter this else branch, it means our writeToFile is broken.
                }
                taskList.add(t);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("An IO error occurred with the data file.");
            e.printStackTrace();
        }
    }

    /**
     * Write newly acquired task data into the storage.
     * 
     * @param filePath The path to the storage to write into.
     * @param taskList The taskList of tasks to add.
     * @throws IOException
     */
    public String writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 1; i <= taskList.size(); i++) {
                Task t = taskList.get(i);
                fw.write(t.taskType);
                fw.write("\n");
                fw.write(t.isDone ? "true" : "false");
                fw.write("\n" + t.description + "\n");
                if (t.taskType == 'D') {
                    fw.write(t.endDate.toString() + "\n");
                } else if (t.taskType == 'E') {
                    fw.write(t.startDate.toString() + "\n");
                    fw.write(t.endDate.toString() + "\n");
                }
            }
            fw.close();
            return "Saved to file!";
        } catch (IOException e) {
            return "Oops, IO Exception when writing to file";
        }
    }
}
