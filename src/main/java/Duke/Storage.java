package Duke;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
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
        if(filePath == null) {
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

            BufferedWriter writer = null; // Initialize writer outside try block
            try {
                writer = new BufferedWriter(new FileWriter(fileReader));

                for (int i = 0; i < taskNum; i++) {
                    writer.write(tasks[i].toSaveString());
                    writer.newLine();
                }
            } finally {
                if (writer != null) {
                    writer.close();
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

                int Sep1 = line.indexOf("|", 4);

                if (taskType == 'T') {
                    String description = line.substring(Sep1 + 2);
                    loadedTasks[loadedTaskNum] = new Todo(description);
                } else if (taskType == 'D') {
                    int Sep2 = line.indexOf(" | ", Sep1 + 3);
                    String description = (Sep2 != -1) ? line.substring(Sep1 + 2, Sep2) : line.substring(Sep2 + 3);

                    String dateString = line.substring(line.lastIndexOf("|") + 1).trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(dateString, formatter);

                    loadedTasks[loadedTaskNum] = new Deadline(description, deadlineDateTime);
                } else if (taskType == 'E') {
                    int Sep2 = line.indexOf(" | ", Sep1 + 3);
                    int to = line.indexOf(" - ", Sep2 + 3);
                    String description = (Sep2 != -1) ? line.substring(Sep1 + 2, Sep2) : line.substring(Sep2 + 3);
                    String from = line.substring(Sep2 + 3, to);
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
