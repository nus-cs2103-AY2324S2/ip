package hal.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class handles reading from and writing to a file.
 */
public class Storage {
    private static final boolean IS_DONE_DEFAULT = false;
    private final File file;
    private final TaskList taskList;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The path to the file.
     * @param taskList The TaskList to manage tasks.
     */
    public Storage(String filePath, TaskList taskList) {
        this.file = new File(filePath);
        this.file.getParentFile().mkdirs();
        this.taskList = taskList;

        try {
            // Ensure the file is created before attempting to read from it
            if (this.file.createNewFile()) {
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }

        } catch (IOException e) {
            System.out.println("Error creating or reading from file");
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks to the file.
     *
     * @param filePath The path to the file.
     */
    public void writeToFile(String filePath) {
        try {
            java.io.FileWriter fw = new java.io.FileWriter(filePath);
            // get tasklist
            for (Task task : taskList.getTaskList()) {
                fw.write(task.getFileString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads tasks from the file.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public ArrayList<Task> readFromFile() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> prevTaskList = new ArrayList<>();

        try {
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(" \\| ");

                String taskType = parts[0];
                boolean isDone = (Integer.parseInt(parts[1]) == 1);
                String description = parts[2];

                switch (taskType) {
                    case "T":
                        prevTaskList.add(new Todo(isDone, description));
                        break;
                    case "D":
                        prevTaskList.add(new Deadline(IS_DONE_DEFAULT, description, parts[3]));
                        break;
                    case "E":
                        prevTaskList.add(new Event(IS_DONE_DEFAULT, description, parts[3], parts[4]));
                        break;
                    default:
                        System.out.println("Unknown task type");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return prevTaskList;
    }
}
