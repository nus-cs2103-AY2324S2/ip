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
    private Parser parserForStorage = new Parser("yyyy-MM-dd");

    /**
     * Load any previously stored task data into the current taskList.
     * 
     * @param taskList The taskList to store this data into.
     */
    public void loadFromFile(TaskList taskList) {
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                this.loadTask(s, taskList);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("An IO error occurred with the data file.");
            e.printStackTrace();
        }
    }

    private void loadTask(Scanner s, TaskList taskList) {
        String task = s.nextLine();
        boolean done = s.nextLine().equals("true");
        String desc = s.nextLine();
        Task t;
        if (task.equals("T")) {
            t = new Todo(done, desc);
        } else if (task.equals("D")) {
            LocalDate deadline = parserForStorage.parseDate(s.nextLine());
            t = new Deadline(done, desc, deadline);
        } else if (task.equals("E")) {
            LocalDate startDate = parserForStorage.parseDate(s.nextLine());
            LocalDate endDate = parserForStorage.parseDate(s.nextLine());
            t = new Event(done, desc, startDate, endDate);
        } else {
            t = null;
            System.out.println("Bad formatting in tasks.txt...");
            assert false; // If we enter this else branch, it means our writeToFile is broken.
        }
        taskList.add(t);
    }

    /**
     * Write newly acquired task data into the storage.
     * 
     * @param taskList The taskList of tasks to add.
     * @throws IOException
     */
    public String writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 1; i <= taskList.size(); i++) {
                Task t = taskList.get(i);
                char taskType = t.getTaskType();
                fw.write(taskType);
                fw.write("\n");
                fw.write(t.isDone ? "true" : "false");
                fw.write("\n" + t.description + "\n");
                if (taskType == 'D') {
                    Deadline d = (Deadline) t;
                    fw.write(d.getDeadline().toString() + "\n");
                } else if (taskType == 'E') {
                    Event e = (Event) t;
                    fw.write(e.getStartDate().toString() + "\n");
                    fw.write(e.getEndDate().toString() + "\n");
                }
            }
            fw.close();
            return "Saved to file!";
        } catch (IOException e) {
            return "Oops, IO Exception when writing to file";
        }
    }
}
