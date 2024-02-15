package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the storage class of the Duke program.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the file.
     * @param tasks The tasks to be saved.
     * @param taskNum The number of tasks to be saved.
     */
    public void saveTasksToFile(Task[] tasks, int taskNum) {
        try {
            File fileReader = new File(filePath);
            if (fileReader.getParentFile().mkdirs()) {
                System.out.println("Directories created successfully.");
            } else {
                System.out.println("Saving it to your already created directories");
            }

            // Initialize writer outside try block
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileReader))) {

                for (int i = 0; i < taskNum; i++) {
                    writer.write(tasks[i].toSaveString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("can't save :((");
        }
    }

    /**
     * Loads the tasks from the file.
     * @return The tasks loaded from the file.
     * @throws IOException If there is an error loading the tasks from the file.
     */
    public Task[] loadTasks() throws IOException {
        Path file = Paths.get(filePath);
        if (!Files.exists(file)) {
            return new Task[100];
        }

        Task[] loadedTasks = new Task[100];
        int loadedTaskNum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char taskType = line.charAt(0);
                boolean isDone = line.charAt(4) == '1';

                int sep1 = line.indexOf("|", 4);

                if (taskType == 'T') {
                    String description = line.substring(sep1 + 2);
                    loadedTasks[loadedTaskNum] = new Todo(description);
                } else if (taskType == 'D') {
                    int sep2 = line.indexOf(" | ", sep1 + 3);
                    String description = (sep2 != -1) ? line.substring(sep1 + 2, sep2)
                            : line.substring(sep2 + 3);

                    String dateString = line.substring(line.lastIndexOf("|") + 1).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(dateString, formatter);

                    loadedTasks[loadedTaskNum] = new Deadline(description, deadlineDateTime);
                } else if (taskType == 'E') {
                    int sep2 = line.indexOf(" | ", sep1 + 3);
                    int to = line.indexOf(" - ", sep2 + 3);
                    String description = (sep2 != -1) ? line.substring(sep1 + 2, sep2)
                            : line.substring(sep2 + 3);
                    String from = line.substring(sep2 + 3, to);
                    String too = line.substring(to + 3);
                    loadedTasks[loadedTaskNum] = new Event(description, from, too);
                }

                if (isDone) {
                    loadedTasks[loadedTaskNum].markAsDone();
                }

                loadedTaskNum++;
            }
        }
        return loadedTasks;
    }
}
