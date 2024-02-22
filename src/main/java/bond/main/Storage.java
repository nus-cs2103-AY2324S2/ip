package bond.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import bond.task.DeadlineTask;
import bond.task.EventTask;
import bond.task.Task;
import bond.task.TaskList;
import bond.task.TodoTask;

/**
 * The Parser class is used to parse user input and create the appropriate
 * Command object.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class Storage {

    private final String pathToFile;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Storage(String filePath) {
        this.pathToFile = filePath;
    }

    /**
     * Parses the name of a TodoTask and creates it.
     *
     * @param taskName The name of the task.
     * @return The TodoTask created.
     */
    private TodoTask readTodo(String taskName) {
        return new TodoTask(taskName);
    }

    /**
     * Parses the date and time of a DeadlineTask and creates it.
     *
     * @param taskName The name of the task.
     * @param taskDeadline  The date and time for deadline of task.
     * @return The DeadlineTask created.
     */
    private DeadlineTask readDeadline(String taskName, String taskDeadline) throws BondException {
        int spaceIndex = taskDeadline.indexOf(" ");
        int closeIndex = taskDeadline.indexOf(")");
        String deadline = taskDeadline.substring(spaceIndex + 1, closeIndex);
        String[] components = deadline.split(" ");
        deadline = Parser.changeDateFormat(components[0], components[1], components[2]) + " " + components[3];

        return new DeadlineTask(taskName, deadline);
    }

    /**
     * Parses the date and time of an EventTask and creates it.
     *
     * @param taskName The name of the task.
     * @param period  The date and time of the start and end of task.
     * @return The EventTask created.
     */
    private EventTask readEventPeriod(String taskName, String period) throws BondException {

        String eventPeriod = period;
        String start;
        String end;

        int closeIndex = eventPeriod.indexOf(")");
        eventPeriod = eventPeriod.substring(0, closeIndex);
        String[] components = eventPeriod.split(" to: ");

        start = components[0].replace("from: ", "");
        end = components[1];

        String[] startComponents = start.split(" ");
        start = Parser.changeDateFormat(startComponents[0], startComponents[1], startComponents[2])
                + " " + startComponents[3];

        String[] endComponents = end.split(" ");
        end = Parser.changeDateFormat(endComponents[0], endComponents[1], endComponents[2])
                + " " + endComponents[3];

        return new EventTask(taskName, start, end);
    }

    /**
     * Parses the task and adds it to the task list.
     * We assume that the task is in the correct format.
     *
     * @param task  The task to be parsed and added to the task list.
     * @param tasks The task list to add the parsed task to.
     * @throws BondException If the task cannot be parsed and added to the task
     *                       list.
     */
    public void readAndAddTask(String task, ArrayList<Task> tasks) throws BondException {
        String remainder = task.substring(4);
        String taskName;
        boolean isMarked = false;
        Task newTask;

        if (remainder.startsWith("[X]")) {
            isMarked = true;
        }

        remainder = remainder.substring(4);

        // Determine task name
        if (!remainder.contains("(")) {
            taskName = remainder;
            remainder = "";
        } else {
            int openIndex = remainder.indexOf("(");
            taskName = remainder.substring(0, openIndex - 1);
            remainder = remainder.substring(openIndex + 1);
        }

        // Add task to taskList
        if (task.startsWith("[T]")) {

            newTask = readTodo(taskName);

        } else if (task.startsWith("[D]")) {

            newTask = readDeadline(taskName, remainder);

        } else if (task.startsWith("[E]")) {

            newTask = readEventPeriod(taskName, remainder);

        } else {
            newTask = null;
            BondException.raiseException("load", "LOAD_FAILURE");
        }

        if (isMarked && newTask != null) {
            newTask.markAsComplete();
        }

        tasks.add(newTask);
    }

    /**
     * Loads the tasks from the file and returns the tasks as an ArrayList.
     *
     * @return The tasks loaded from the file as an ArrayList.
     * @throws BondException If the tasks cannot be loaded from the file.
     */
    protected ArrayList<Task> load() throws BondException {

        File f;
        Scanner sc;
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {

            // Check for directory / file existence
            String home = System.getProperty("user.home");
            java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "data");
            boolean directoryExists = java.nio.file.Files.exists(directoryPath);

            if (!directoryExists) {
                java.nio.file.Files.createDirectory(directoryPath);
            }

            java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "Bond.txt");
            boolean fileExists = java.nio.file.Files.exists(filePath);

            if (!fileExists) {
                java.nio.file.Files.createFile(filePath);
            }

            assert java.nio.file.Files.exists(directoryPath) : "Directory should exist";
            assert java.nio.file.Files.exists(filePath) : "File should exist";

            f = new File(pathToFile); // create a File for the given file path
            sc = new Scanner(f); // create a Scanner using the File as the source

        } catch (IOException e) {
            BondException.raiseException("load", "LOAD_FAILURE");
            return null;
        }

        while (sc.hasNextLine()) {
            String currTask = sc.nextLine();
            readAndAddTask(currTask, loadedTasks);
        }

        sc.close();

        return loadedTasks;
    }

    /**
     * Stores the new task in the file.
     *
     * @param newTask  The new task to be stored in the file.
     * @throws BondException If the new task cannot be stored in the file.
     */
    public void storeTask(Task newTask) throws BondException {
        try {
            FileWriter fw = new FileWriter(this.pathToFile, true); // create a FileWriter in append mode
            fw.write(newTask.toString());
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            BondException.raiseException("store", "STORE_FAILURE");
        }
    }

    /**
     * Overwrites the previous save in the file with the new task list.
     *
     * @param taskList The new task list to overwrite the previous save in the file.
     * @throws BondException If the new task list cannot overwrite the previous save
     *                       in the file.
     */
    public void overwritePreviousSave(TaskList taskList) throws BondException {
        try {
            FileWriter fw = new FileWriter(pathToFile, false); // create a FileWriter in overwrite mode
            ListIterator<Task> toWrite = taskList.getTasks();

            while (toWrite.hasNext()) {
                fw.write(toWrite.next().toString());
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            BondException.raiseException("store", "STORE_FAILURE");
        }
    }
}
