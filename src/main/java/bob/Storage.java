package bob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import bob.exception.LoadingException;
import bob.exception.SavingException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.Todo;

public class Storage {
    private static final String DATA_DIR = "data";
    public static final String DATA_PATH = DATA_DIR + "/bob.txt";

    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private File dataFile;

    private static File createOrRetrieve(String dataPath) throws IOException {
        Path path = Paths.get(dataPath);
        Path parent = path.getParent();
        Files.createDirectories(parent);
        if (Files.notExists(path)) {
            return Files.createFile(path).toFile();
        }
        return path.toFile();
    }

    // TODO: Once extractParameter is more generalised, we can move this to Parser
    private static Task parseStorageLine(String line) throws LoadingException {
        // Split the line with  " | " as separator
        String[] parameters = line.split(" \\| ");

        // The first part of the split indicates the task type
        String taskType = parameters[0];

        // The second part of the split indicates whether the task is done, and therefore should only be true or false
        if (!parameters[1].equals("true") && !parameters[1].equals("false")) {
            throw new LoadingException("invalid value for isDone detected");
        }

        // Store the second part of the split
        boolean isDone = Boolean.parseBoolean(parameters[1]);
        return getTask(parameters, taskType, isDone);
    }

    private static Task getTask(String[] parameters, String taskType, boolean isDone) throws LoadingException {
        // TODO: Add JavaDoc header comment
        // The third part of the split indicates the task description
        String description = parameters[2];

        // Parse the remaining parts of the split according to the task type
        Task task;
        switch (taskType) {
        case Todo.STORAGE_INDICATOR:
            task = new Todo(description);
            break;
        case Deadline.STORAGE_INDICATOR:
            LocalDateTime by = LocalDateTime.parse(parameters[3], FORMATTER_DATE_TIME);
            task = new Deadline(description, by);
            break;
        case Event.STORAGE_INDICATOR:
            LocalDateTime from = LocalDateTime.parse(parameters[3], FORMATTER_DATE_TIME);
            LocalDateTime to = LocalDateTime.parse(parameters[4], FORMATTER_DATE_TIME);
            task = new Event(description, from, to);
            break;
        default:
            throw new LoadingException("invalid storage indicator detected");
        }
        task.setDone(isDone);
        return task;
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER_DATE_TIME);
    }

    public ArrayList<Task> load(String dataPath) throws LoadingException {
        try {
            // Create or retrieve the data file
            dataFile = createOrRetrieve(dataPath);

            // Read the data file and load its content into tasks.
            try (Scanner s = new Scanner(dataFile)) {
                ArrayList<Task> tasks = new ArrayList<>();
                while (s.hasNext()) {
                    tasks.add(parseStorageLine(s.nextLine()));
                }
                return tasks;
            }
        } catch (Exception e) {
            // Any exception caught here should just be displayed as a server-side error
            throw new LoadingException(e.getMessage());
        }
    }

    public void saveTask(Task task) throws SavingException {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile(), true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(task.getStorageFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            // Any exception caught here should just be displayed as a server-side error
            throw new SavingException(e.getMessage());
        }
    }

    public void refresh(ArrayList<Task> tasks) throws SavingException {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for (Task task : tasks) {
                    bw.write(task.getStorageFormat());
                    bw.newLine();
                }
                bw.flush();
            }
        } catch (IOException e) {
            // Any exception caught here should just be displayed as a server-side error
            throw new SavingException(e.getMessage());
        }
    }
}
