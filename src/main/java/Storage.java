import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Storage {
    private static final String DIRECTORY_PATH = "./data/";
    private static final String FILE_PATH = "./data/duke.txt";
    private File file;
    private static final Pattern TODO_PATTERN = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?)");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("([A-Z]) \\| (\\d) \\| (.*?) \\| (.*?) \\| (.*?)");

    public Storage() throws IOException {
        this.file = new File(FILE_PATH);
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs();
            if (!isCreated) {
                System.out.println("Failed to create directory: " + DIRECTORY_PATH);
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

    public static void saveData(ArrayList<Task> taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
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

    public static ArrayList<Task> loadData() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
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

    private static Task parseTask(String line) {
        Matcher todoMatcher = TODO_PATTERN.matcher(line);
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(line);
        Matcher eventMatcher = EVENT_PATTERN.matcher(line);
        if (eventMatcher.matches()) {
            boolean isCompleted = eventMatcher.group(2).equals("1");
            String taskName = " " + eventMatcher.group(3);
            String start = eventMatcher.group(4);
            String end = eventMatcher.group(5);
            Event eventTask = new Event(taskName, start, end);
            if (isCompleted) {
                eventTask.complete();
            }
            return eventTask;
        } else if (deadlineMatcher.matches()) {
            boolean isCompleted = deadlineMatcher.group(2).equals("1");
            String taskName = " " + deadlineMatcher.group(3);
            String deadline = deadlineMatcher.group(4);
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