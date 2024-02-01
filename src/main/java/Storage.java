import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Encapsulates the data and behaviour of the user's save file.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Storage {
    private static final String PATH_DIRECTORY = "./data/";
    private static final String PATH_FILE = "./data/duke.txt";
    private static final Pattern PATTERN_TODO = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?)");
    private static final Pattern PATTERN_DEADLINE = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?)");
    private static final Pattern PATTERN_EVENT = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?) \\| (.*?)");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a new Storage object.
     * Initialises the new Storage object by creating a directory and file if they do not already exist.
     *
     * @throws IOException if an I/O error occurs during creation of the file.
     */
    public Storage() throws IOException {
        File file = new File(PATH_FILE);
        File directory = new File(PATH_DIRECTORY);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (!isCreated) {
                System.out.println("Failed to create directory: " + PATH_DIRECTORY);
            }
        }
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Saves any modified data in the task list into the save file.
     *
     * @param taskList The ArrayList containing the task list's data.
     */
    public static void saveData(ArrayList<Task> taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_FILE));
            for (Task task : taskList) {
                String taskMessage = task.saveTask();
                bw.write(taskMessage);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    /**
     * Loads the data in the save file into an ArrayList.
     *
     * @return The ArrayList containing the saved data of the user's task list.
     */
    public static ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
        return taskList;
    }

    /**
     * Parses the inputted line for the task data.
     *
     * @param line The line to be parsed.
     * @return The Task containing the task data.
     */
    private static Task parseTask(String line) {
        Matcher todoMatcher = PATTERN_TODO.matcher(line);
        Matcher deadlineMatcher = PATTERN_DEADLINE.matcher(line);
        Matcher eventMatcher = PATTERN_EVENT.matcher(line);
        if (eventMatcher.matches()) {
            boolean isCompleted = eventMatcher.group(2).equals("1");
            String taskName = " " + eventMatcher.group(3);
            LocalDateTime start = LocalDateTime.parse(eventMatcher.group(4), formatter);
            LocalDateTime end = LocalDateTime.parse(eventMatcher.group(5), formatter);
            Event eventTask = new Event(taskName, start, end);
            if (isCompleted) {
                eventTask.complete();
            }
            return eventTask;
        } else if (deadlineMatcher.matches()) {
            boolean isCompleted = deadlineMatcher.group(2).equals("1");
            String taskName = " " + deadlineMatcher.group(3);
            LocalDateTime deadline = LocalDateTime.parse(deadlineMatcher.group(4), formatter);
            Deadline deadlineTask = new Deadline(taskName, deadline);
            if (isCompleted) {
                deadlineTask.complete();
            }
            return deadlineTask;
        } else if (todoMatcher.matches()) {
            boolean isCompleted = todoMatcher.group(2).equals("1");
            String taskName = " " + todoMatcher.group(3);
            ToDo todoTask = new ToDo(taskName);
            if (isCompleted) {
                todoTask.complete();
            }
            return todoTask;
        } else {
            System.out.println("Line does not match expected pattern: " + line);
            return null;
        }
    }
}