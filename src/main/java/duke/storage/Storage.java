package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private static TaskList taskList;
    private static final String HOME = System.getProperty("user.home"); // user's home path
    private static final Path FILEPATH = Paths.get(HOME, "Downloads", "NUS_CS",
            "CS2103", "duke.txt"); // file path of local tasklist file


    /**
     * A default constructor class to initialize Storage object
     */
    public Storage() {
        this.taskList = retrieveList();
    }

    /**
     * Retrieve local file with a list of tasks.
     * @return ArrayList<Task> taskList
     * @throws IOException
     */
    public static TaskList retrieveList() {
        if (Files.exists(FILEPATH)) {
            ArrayList<Task> taskList = new ArrayList<>();
            try {
                BufferedReader br = Files.newBufferedReader(FILEPATH);
                String currentLine = null;
                while ((currentLine = br.readLine()) != null) {
                    String[] inputs = currentLine.split(",");
                    try {
                        boolean isDone = Boolean.parseBoolean(inputs[1]);

                        switch (inputs[0]) {
                            case "T":
                                ToDo t = new ToDo(inputs[2], isDone);
                                taskList.add(t);
                                break;
                            case "D":
                                LocalDateTime by = LocalDateTime.parse(inputs[3], Parser.dateTimeFormatter);
                                Deadline d = new Deadline(inputs[2], isDone, by);
                                taskList.add(d);
                                break;

                            case "E":
                                LocalDateTime from = LocalDateTime.parse(inputs[3], Parser.dateTimeFormatter);
                                LocalDateTime to = LocalDateTime.parse(inputs[4], Parser.dateTimeFormatter);
                                Event e = new Event(inputs[0], isDone, from, to);
                                taskList.add(e);
                                break;
                        }
                    } catch (DateTimeException e) {
                        // System.out.println("LOG: Dates of tasks corrupted.");
                        Ui.showLoadingError();
                        return new TaskList();
                    }
                }
            } catch (IOException e) {
                // System.out.println("LOG: File cannot be read, empty list");
            }
            return new TaskList(taskList);
        } else {
            //System.out.println("LOG: File not found, empty list");
            Ui.showLoadingError();
            return new TaskList();
        }
    }

    /**
     * Update local file contents.
     */
    public static void updateFile() {
        createFile();
        ArrayList<String> taskContent = taskList.getTasksInStoreList();
        try {
            Files.write(FILEPATH, taskContent);
            // System.out.println("LOG: Updated file contents.");
        } catch (IOException e) {
            // System.out.println("LOG: File cannot be found.");
        }

    }

    /**
     * Create new local file if it does not exist.
     */
    public static void createFile() {
        try {
            Files.createFile(FILEPATH);
            //  System.out.println("LOG: File created");
        }
        catch (IOException e) {
            //  System.out.println("LOG: File already exists.");
        }

    }

    public Task getTaskFromTaskNo(int taskNo) {
        return taskList.getTask(taskNo);
    }
    public void addTask(Task t) {
        taskList.addTask(t);
        updateFile();
    }

    public void markTask(int taskNo) {
        taskList.markTask(taskNo);
        updateFile();
    }

    public void unmarkTask(int taskNo) {
        taskList.unmarkTask(taskNo);
        updateFile();
    }

    public int getNumOfTasks() {
        return taskList.getNoOfTasks();
    }

    public String getTaskStringFromTaskNo(int taskNo) {
        return taskList.get(taskNo).toString();
    }

    public String getTasksInString() {
        return taskList.toString();
    }

    public TaskList filterListByKeyword(String keyword) {
        return taskList.filterTasksByKeyword(keyword);
    }
}
