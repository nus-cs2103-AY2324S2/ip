import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    private final String storagePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The file path of the storage file.
     */
    public Storage(String filePath) {
        this.storagePath = filePath;
    }

    /**
     * Writes the contents of the task list into the file.
     *
     * @throws JimmyException If the file cannot be written to.
     */
    public void writeToFile(ArrayList<Task> taskList) throws JimmyException {
        try {
            FileWriter fileWriter = new FileWriter(storagePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            System.out.println("Writing to file...");
            for (Task task : taskList) {
                bufferedWriter.write(task.toFileString() + System.lineSeparator());
            }
            bufferedWriter.close();
            System.out.println("Done with writing!");
        } catch (IOException e) {
            throw new JimmyException("IOException: Cannot write to file.");
        }
    }

    /**
     * Loads the contents of the file into memory.
     *
     * @throws JimmyException If the file cannot be loaded.
     */
    public void loadFileContents(ArrayList<Task> taskList) throws JimmyException {
        try {
            System.out.println("Loading file contents...");
            File file = new File(storagePath);

            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            if (isFolderMade || isFileMade) {
                System.out.println("File not found, Jimmy is creating one for you now...");
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Task savedTask = parseFileString(line);
                taskList.add(savedTask);

            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new JimmyException("IOException: Cannot load file.");
        }
    }

    /**
     * Parses the file string and appends task from memory into task list.
     *
     * @param fileString The string to be parsed.
     */
    public Task parseFileString(String fileString) throws JimmyException {
        String[] attributes = fileString.split(" \\| "); // split by delimiter to obtain the task name, status and timings (if any)
        String taskType = attributes[0];
        boolean isTaskCompleted = Objects.equals(attributes[1], "1");

        try {
            switch (taskType) {
            case "T":
                String taskDesc = attributes[2];
                return new Todo(taskDesc, isTaskCompleted);
            case "D":
                String deadlineDesc = attributes[2];
                String deadline = attributes[3];
                return new Deadline(deadlineDesc, deadline, isTaskCompleted);
            case "E":
                String eventDesc = attributes[2];
                String start = attributes[3];
                String end = attributes[4];
                return new Event(eventDesc, start, end, isTaskCompleted);
            }
        } catch (DateTimeParseException e) {
            throw new JimmyException("Error: Cannot parse the date saved locally.");
        }
        return null;
    }
}
