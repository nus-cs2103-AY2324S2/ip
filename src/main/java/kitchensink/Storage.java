package kitchensink;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import kitchensink.exception.SaveFileCorruptedException;
import kitchensink.task.Deadline;
import kitchensink.task.Event;
import kitchensink.task.Task;
import kitchensink.task.ToDo;

/**
 * Saves and loads tasks based on a save file specified by a path.
 */
public class Storage {
    private String fileName;

    /**
     * Creates a directory for the save file if it does not exist, and creates a save file if it does not exist.
     * @param fileName The path to the save file (in String type).
     * @throws IOException As it may create a new file.
     */
    public Storage(String fileName) throws IOException {
        assert fileName != null;
        this.fileName = fileName;
        File f = new File(fileName);
        if (!f.exists()) {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            f.createNewFile();
        }
        assert f.exists();
    }

    /**
     * Saves/Writes the taskList String representation to the save file.
     * @param taskList The taskList that is saved to the save file.
     * @throws IOException As it writes to file.
     */
    public void saveTasks(List taskList) throws IOException {
        assert taskList != null;
        int startingLength = taskList.getListSize();
        String newFileContent = taskList.toString();
        assert new File(fileName).exists();
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(newFileContent);
        writer.close();
        assert taskList.getListSize() - startingLength == 1;
    }

    /**
     * Loads the save file content as a Task ArrayList.
     * This is done by reading and parsing line-by-line from the save file, then adding tasks to the resultant taskList.
     * @return Task ArrayList that is to be loaded to a taskList.
     * @throws IOException As it reads a file.
     */
    public ArrayList<Task> loadTasks() throws IOException, SaveFileCorruptedException {
        assert new File(fileName).exists();
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            String taskType;
            boolean isDone;
            try {
                taskType = currLine.split("]")[0].split("\\[")[1];
                isDone = Objects.equals(currLine.split("\\[")[2].split("]")[0], "X");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SaveFileCorruptedException();
            }
            String description;
            switch (taskType) {
            case "T":
                try {
                    description = currLine.split("] ")[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SaveFileCorruptedException();
                }
                tasks.add(new ToDo(description));
                break;
            case "D":
                String dueDate;
                try {
                    description = currLine.split(" \\(by:")[0].split("] ")[1];
                    dueDate = currLine.split("\\(by: ")[1].split("\\)")[0];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new SaveFileCorruptedException();
                }
                tasks.add(new Deadline(description, LocalDateTime.parse(dueDate,
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
                break;
            case "E":
                String startDate;
                String endDate;
                try {
                    description = currLine.split(" \\(from:")[0].split("] ")[1];
                    startDate = currLine.split("\\(from: ")[1].split(" to:")[0];
                    endDate = currLine.split("to: ")[1].split("\\)")[0];
                }  catch (ArrayIndexOutOfBoundsException e) {
                    throw new SaveFileCorruptedException();
                }
                tasks.add(new Event(description, LocalDateTime.parse(startDate,
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                        LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
                break;
            default:
                throw new SaveFileCorruptedException();
            }
            if (isDone) {
                tasks.get(tasks.size() - 1).setToDone();
            }
        }
        return tasks;
    }

    /**
     * Clears the save file.
     * This is only used for testing so that each test can start with a clean save file, instead of creating a new path
     * for a new save file for each test.
     * @throws IOException As it writes to a file.
     */
    public void clearData() throws IOException {
        assert false; // disable this before running ListTest or StorageTest
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
    }
}
