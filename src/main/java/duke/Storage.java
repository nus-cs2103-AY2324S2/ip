package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.exceptions.FileCorruptionException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import duke.tasks.Task;

/**
 * Storage class to handle file operations
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage class
     * @param filePath Path to file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads file from path
     * 
     * @return File object
     * @throws IOException
     */
    public File loadFile() throws IOException {
        File data = new File(filePath);
        data.getParentFile().mkdirs();
        data.createNewFile();
        return data;
    }

    /**
     * Creates task list from file
     * 
     * @return TaskList object
     * @throws FileCorruptionException
     * @throws IOException
     */
    public TaskList createTaskList() throws FileCorruptionException, IOException {
        File file = this.loadFile();
        TaskList taskList = new TaskList(file);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = Storage.parseTaskData(taskData);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileCorruptionException("Unable to read file");
        }
        return taskList;
    }

    private static Task parseTaskData(String data) throws Exception {
        String[] fields = data.split("\\|");
        assert fields.length > 0 : "fields should not be empty";
        Task t;

        switch (fields[0]) {
        case "T":
            t = Storage.parseTodoData(fields);
            break;
        case "D":
            t = Storage.parseDeadlineData(fields);
            break;
        case "E":
            t = Storage.parseEventData(fields);
            break;
        default:
            throw new Exception();
        }
        return t;
    }

    private static ToDo parseTodoData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new ToDo(fields[1], isDone);
    }

    private static Deadline parseDeadlineData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new Deadline(fields[1], isDone, fields[3], false);
    }

    private static Event parseEventData(String[] fields) {
        boolean isDone = (Integer.valueOf(fields[2]) == 1);
        return new Event(fields[1], isDone, fields[3], fields[4], false);
    }



}
