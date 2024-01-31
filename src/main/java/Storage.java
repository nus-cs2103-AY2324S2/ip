import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Handles all loading and saving operations.
 * @author Tan Qin Yong
 */
public class Storage {

    /** The file path where Duke's task list is stored. */
    private static final String filePath = "./data/duke.txt";

    /**
     * Saves the given task list to the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @param taskList The task list to be saved.
     */
    public static void saveToFile(TaskList taskList) {
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(filePath);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath);

            for (int i = 1; i <= taskList.getSize(); i++) {
                Task currentTask = taskList.getTask(i);
                String type = currentTask.getType();
                int isDone = 0;
                if (currentTask.isDone()) {
                    isDone = 1;
                }

                switch (type) {
                case "todo": {
                    fw.write(type + "|" + isDone + "|" + currentTask.getDescription());
                    break;
                }
                case "event": {
                    Event currentEvent = (Event) currentTask;
                    fw.write(type + "|" + isDone + "|" + currentEvent.getDescription() + "|" +
                            currentEvent.getFrom() + "|" + currentEvent.getTo());
                    break;
                }
                case "deadline": {
                    Deadline currentDeadline = (Deadline) currentTask;
                    fw.write(type + "|" + isDone + "|" + currentDeadline.getDescription() + "|" +
                            currentDeadline.getBy());
                    break;
                }
                }
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException io) {
            System.out.println("Error saving file to disk. Exiting...");
        }
    }

    /**
     * Loads the task list from the file specified by the file path.
     * Creates the necessary directories and file if they don't exist.
     *
     * @return The task list loaded from the file.
     */
    public static TaskList loadFile() {
        TaskList taskList = new TaskList();
        try {
            // These will do nothing if files already exists.
            Files.createDirectories(Paths.get("./data"));
            File newFile = new File(filePath);
            if (!newFile.exists()) {
                boolean success = newFile.createNewFile();
            }
            Scanner sc = new Scanner(newFile);

            while (sc.hasNext()) {
                String next = sc.nextLine();
                String[] taskArr = next.split("\\|");
                String type = taskArr[0];
                String description = taskArr[2];

                Task newTask = new Task();

                switch (type) {
                case "todo": {
                    newTask = new ToDo(description);
                    break;
                }
                case "deadline": {
                    String by = taskArr[3];

                    LocalDate byParsedDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    newTask = new Deadline(description, byParsedDate);
                    break;
                }
                case "event": {
                    String from = taskArr[3];
                    String to = taskArr[4];

                    LocalDate fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    newTask = new Event(description, fromDate, toDate);
                    break;
                }
                }

                int numDone = Integer.parseInt(taskArr[1]);
                if (numDone == 1) {
                    newTask.markAsDone();
                }
                taskList.addTask(newTask, true);
            }

            sc.close();
        } catch (IOException io) {
            System.out.println("Error retrieving data from disk. Exiting...");
        }
        return taskList;
    }
}
