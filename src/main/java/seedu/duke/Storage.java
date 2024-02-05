package seedu.duke;

import seedu.duke.task.Task;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * <h1> Storage </h1>
 * This Storage class loads tasks from the data file on the hard disk on initialization
 * of the Duke program. It also saves and stores the changes made to the tasks onto the same file in the hard disk
 * of the user, including the adding of new tasks, deleting and changing of markings.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Storage {
    private static File f;
    private static String filePath;
    private static TaskList taskList;
    public Storage(String filePath, TaskList taskList) { //constructor
        this.filePath = filePath;
        f = new File(filePath);
        this.taskList = taskList;
    }

    /**
     * Scans the data from the file the holds the information of the existing tasks
     * and adds it to the TaskList object.
     *
     * @throws FileNotFoundException if the file does not exist in the file path.
     */
    public void loadFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(f);
        ArrayList<Task> tasksToLoad  = new ArrayList<>();
        while(scanner.hasNextLine()) {
            String task = scanner.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task newTask;
            if (taskDetails.length == 3) { //todo task
                newTask = new Task(taskDetails[2]);
            } else if (taskDetails.length == 4) { //deadline
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM uuuu HHmm");
                LocalDateTime deadline = LocalDateTime.parse(taskDetails[3], formatter);
                newTask = new Deadline(taskDetails[2], deadline);
            } else {
                newTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
            }
            if (taskDetails[1].equals("1")) { //if task/event/deadline is marked
                newTask.markDone(false);
            }
            tasksToLoad.add(newTask);
        }
        scanner.close();
        tasksToLoad.forEach(x -> this.taskList.addItem(x, false));
    }

    /**
     * Writes the inputted string to the file on the hard disk.
     *
     * @param textToAdd the information to be written to the file
     * @throws IOException if an error occurs while writing to the file.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Deletes the line of information in the file that stores the details of the task at the index num
     * by rewriting a copy of the entire file to the same filepath and not writing the line that is to be deleted.
     *
     * @param num the line of information in the file that is to be deleted,
     *            corresponds with the index of the task in the task list to be deleted.
     * @throws IOException if an error occurs while writing to the file.
     */
    public static void deleteFromFile(int num) throws IOException {
        Scanner s = new Scanner(f);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            }
            count++;
        }
        FileWriter fw = new FileWriter(f);
        fw.write(newData);
        fw.close();
    }

    /**
     * Updates the marking information stored in the file by rewriting a copy of the entire file to the
     * same file path and writing the new line of information where the marking has changed.
     *
     * @param num the line of information in the file that is to be updated, corresponds with
     *            the index of the task in the task list whose marking is to be updated.
     * @param textToReplace the new text to replace the old line of code, which contains
     *                      the newly updated marking information. Taken from the Task objects toStore() function.
     * @throws IOException if an error occurs while writing to the file.
     */
    public static void changeMarking(int num, String textToReplace) throws IOException {
        Scanner s = new Scanner(f);
        String newData = "";
        int count = 0;
        while (s.hasNextLine()) {
            String temp = s.nextLine();
            if (count != num) {
                newData += temp + "\n";
            } else {
                newData += textToReplace;
            }
            count++;
        }
        FileWriter fw = new FileWriter(f);
        fw.write(newData);
        fw.close();
    }

    /**
     * Writes the information of the newly added task to the file
     *
     * @param task the Task object whose information is to be stored.
     */
    public static void add(Task task) { //to append items to taskList
        try {
            writeToFile(task.toStore());
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }

    /**
     * Deletes the information of the task to be deleted from the file.
     *
     * @param num the index of the task to be deleted, which corresponds with the
     *            index of the line that contains the information to be deleted.
     */
    public static void delete(int num) {
        try {
            deleteFromFile(num);
        } catch (IOException e) {
            System.out.println("Oops something went wrong.\n" + e.getMessage());
        }
    }
}
