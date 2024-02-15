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
import bond.task.ToDoTask;

/**
 * The Parser class is used to parse user input and create the appropriate
 * Command object.
 *
 * @author Benny Loh
 * @version 0.1
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
     * Parses the task and adds it to the task list.
     *
     * @param task  The task to be parsed and added to the task list.
     * @param tasks The task list to add the parsed task to.
     * @throws BondException If the task cannot be parsed and added to the task
     *                       list.
     */
    public void parseAndAddTask(String task, ArrayList<Task> tasks) throws BondException {
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
            newTask = new ToDoTask(taskName);

        } else if (task.startsWith("[D]")) {
            int spaceIndex = remainder.indexOf(" ");
            int closeIndex = remainder.indexOf(")");
            String deadline = remainder.substring(spaceIndex + 1, closeIndex);
            String[] components = deadline.split(" ");
            deadline = Parser.changeDateFormat(components[0], components[1], components[2]) + " " + components[3];

            newTask = new DeadlineTask(taskName, deadline);

        } else if (task.startsWith("[E]")) {
            String start = "";
            String end = "";
            int closeIndex = remainder.indexOf(")");
            remainder = remainder.substring(0, closeIndex);
            String[] components = remainder.split(" ");

            for (int i = 0; i < components.length; i++) {

                if (components[i].equals("from:")) {

                    for (int j = i + 1; j < components.length; j++) {
                        if (components[j].equals("to:")) {
                            break;
                        }
                        start += components[j] + " ";
                    }

                } else if (components[i].equals("to:")) {

                    for (int k = i + 1; k < components.length; k++) {
                        end += components[k] + " ";
                    }

                }

            }

            String[] startComponents = start.split(" ");
            start = Parser.changeDateFormat(startComponents[0], startComponents[1], startComponents[2])
                    + " " + startComponents[3];
            String[] endComponents = end.split(" ");
            end = Parser.changeDateFormat(endComponents[0], endComponents[1], endComponents[2])
                    + " " + endComponents[3];

            newTask = new EventTask(taskName, start, end);

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
    public ArrayList<Task> load() throws BondException {

        try {

            ArrayList<Task> loadedTasks = new ArrayList<>();

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

            File f = new File(pathToFile); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNextLine()) {
                String currTask = s.nextLine();
                parseAndAddTask(currTask, loadedTasks);
            }

            s.close();

            return loadedTasks;

        } catch (IOException e) {
            BondException.raiseException("load", "LOAD_FAILURE");
            return null;
        }
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
