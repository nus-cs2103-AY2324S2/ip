package luke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import luke.exception.SaveFileCorruptedException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.ToDo;

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
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            String sampleData = "1.[T][X] eat\n"
                    + "2.[D][ ] homework (by: 02 Feb 2024 23:59)\n"
                    + "3.[E][ ] study (from: 02 Feb 2024 08:00 to: 02 Feb 2024 12:00)";
            writer.write(sampleData);
            writer.close();
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
        int changeInLength = taskList.getListSize() - startingLength;
        assert changeInLength == 1 || changeInLength == -1; // disable this before running ListTest or StorageTest
    }

    /**
     * Loads the save file content as a Task ArrayList.
     * This is done by reading and parsing line-by-line from the save file, then adding tasks to the resultant taskList.
     * Technically, it does not use the task index at the start of each line, but this is to match the tasks displayed
     * by the UI for consistency.
     * @return Task ArrayList that is to be loaded to a taskList.
     * @throws IOException As it reads a file.
     */
    public ArrayList<Task> loadTasks() throws IOException, SaveFileCorruptedException {
        assert new File(fileName).exists();
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String currLine;
        while ((currLine = reader.readLine()) != null) {
            String taskType = getTaskType(currLine);
            boolean isDone = getIsDone(currLine);
            switch (taskType) {
            case "T":
                addToDo(currLine, tasks);
                break;
            case "D":
                addDeadline(currLine, tasks);
                break;
            case "E":
                addEvent(currLine, tasks);
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

    private boolean getIsDone(String currLine) throws SaveFileCorruptedException {
        boolean isDone;
        try {
            isDone = currLine.split("\\[")[2].split("]")[0].equals("X");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileCorruptedException();
        }
        return isDone;
    }

    private String getTaskType(String currLine) throws SaveFileCorruptedException {
        String taskType;
        try {
            taskType = currLine.split("]")[0].split("\\[")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileCorruptedException();
        }
        return taskType;
    }

    private void addToDo(String currLine, ArrayList<Task> tasks) throws SaveFileCorruptedException {
        String description;
        try {
            description = currLine.split("] ")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileCorruptedException();
        }
        tasks.add(new ToDo(description));
    }

    private void addDeadline(String currLine, ArrayList<Task> tasks) throws SaveFileCorruptedException {
        String description;
        String dueDate;
        try {
            description = currLine.split(" \\(by:")[0].split("] ")[1];
            dueDate = currLine.split("\\(by: ")[1].split("\\)")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileCorruptedException();
        }
        tasks.add(new Deadline(description, LocalDateTime.parse(dueDate,
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
    }

    private void addEvent(String currLine, ArrayList<Task> tasks) throws SaveFileCorruptedException {
        String description;
        String startDate;
        String endDate;
        try {
            description = currLine.split(" \\(from:")[0].split("] ")[1];
            startDate = currLine.split("\\(from: ")[1].split(" to:")[0];
            endDate = currLine.split("to: ")[1].split("\\)")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SaveFileCorruptedException();
        }
        tasks.add(new Event(description, LocalDateTime.parse(startDate,
                DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))));
    }

    /**
     * Clears the save file.
     * This is only used for testing so that each test can start with a clean save file, instead of creating a new path
     * for a new save file for each test.
     * @throws IOException As it writes to a file.
     */
    public void clearData() throws IOException {
        // assert false; // disable this before running ListTest or StorageTest
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("");
        writer.close();
    }
}
