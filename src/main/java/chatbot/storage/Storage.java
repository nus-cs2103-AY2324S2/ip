package chatbot.storage;

import chatbot.exception.DukeException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

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
    private static String PATH_FILE;
    private static final Pattern PATTERN_TODO = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?)");
    private static final Pattern PATTERN_DEADLINE = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?)");
    private static final Pattern PATTERN_EVENT = Pattern.compile(
            "([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?) \\| (.*?)");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a new chatbot.storage.Storage object.
     * Initialises the new chatbot.storage.Storage object by creating a directory and file if they do not already exist.
     *
     * @throws IOException if an I/O error occurs during creation of the file.
     */
    public Storage(String filePath) throws DukeException {
        File file = new File(filePath);
        PATH_FILE = filePath;
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                String exceptionMessage = "Error creating file: " + e.getMessage();
                throw new DukeException(exceptionMessage);
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
    public static ArrayList<Task> loadData() throws DukeException {
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
            String exceptionMessage = "Error loading data from file: " + e.getMessage();
            throw new DukeException(exceptionMessage);
        }
        return taskList;
    }

    /**
     * Parses the inputted line for the task data.
     *
     * @param line The line to be parsed.
     * @return The chatbot.task.Task containing the task data.
     */
    private static Task parseTask(String line) {
        assert line != null : "line should not be empty";
        Matcher todoMatcher = PATTERN_TODO.matcher(line);
        Matcher deadlineMatcher = PATTERN_DEADLINE.matcher(line);
        Matcher eventMatcher = PATTERN_EVENT.matcher(line);
        if (eventMatcher.matches()) {
            boolean isCompleted = eventMatcher.group(2).equals("1");
            String taskName = " " + eventMatcher.group(3);
            LocalDateTime start = LocalDateTime.parse(eventMatcher.group(4), FORMATTER);
            LocalDateTime end = LocalDateTime.parse(eventMatcher.group(5), FORMATTER);
            Event eventTask = new Event(taskName, start, end);
            if (isCompleted) {
                eventTask.complete();
            }
            return eventTask;
        } else if (deadlineMatcher.matches()) {
            boolean isCompleted = deadlineMatcher.group(2).equals("1");
            String taskName = " " + deadlineMatcher.group(3);
            LocalDateTime deadline = LocalDateTime.parse(deadlineMatcher.group(4), FORMATTER);
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