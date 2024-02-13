package dibo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import dibo.exception.DiboException;
import dibo.task.Deadline;
import dibo.task.Event;
import dibo.task.Task;
import dibo.task.ToDo;

/**
 * The class to load data from and save data to the text file.
 */
public class Storage {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final String filePath;

    /**
     * Constructs the Storage class.
     *
     * @param filePath The relative file path to the text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the text file and returns an ArrayList.
     *
     * @return An ArrayList to be used to instantiate the TaskList object.
     * @throws DiboException If an error occurs when loading.
     */
    public ArrayList<Task> loadData() throws DiboException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File(this.filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String taskDetails = sc.nextLine();
                String[] details = taskDetails.split("\\|");

                String type = details[0].trim();
                Task task;
                switch (type) {
                case "todo":
                    task = loadTodo(details);
                    break;
                case "deadline":
                    task = loadDeadline(details);
                    break;
                case "event":
                    task = loadEvent(details);
                    break;
                default:
                    throw new DiboException("Sorry sir! Unfortunately the loaded text file "
                            + "contains an invalid task type :O");
                }

                markIfNeeded(task, details);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            createNewFile();
        } catch (DateTimeParseException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " The dates are not in the correct format. It ought to be: yyyy-mm-dd  :(");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " You might want to take a look at the formatting of the text file :(");
        }

        return tasks;
    }

    /**
     * Saves the data from the task list to the text file.
     *
     * @param taskList The updated taskList.
     * @throws DiboException If an error occurs when saving.
     */
    public void saveData(TaskList taskList) throws DiboException {
        try {
            String updatedData = taskList.getSaveFormat();
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(updatedData);
            fw.close();
        } catch (IOException e) {
            throw new DiboException("Oh no sir! We are unable to update the task lists.\n"
                    + "We are terminating the chatbot :(. Check the file again and restart.\n"
                    + "We will be waiting for you here :D.");
        }
    }

    private ToDo loadTodo(String[] details) throws IndexOutOfBoundsException {
        String descriptionToDo = details[2];
        return new ToDo(descriptionToDo);
    }

    private Deadline loadDeadline(String[] details) throws IndexOutOfBoundsException {
        String descriptionDeadline = details[2];
        String by = details[3].trim();
        LocalDate byDate = convertToLocalDate(by);
        return new Deadline(descriptionDeadline, byDate);
    }

    private Event loadEvent(String[] details) throws IndexOutOfBoundsException {
        String descriptionEvent = details[2];
        String start = details[3].trim();
        String end = details[4].trim();
        LocalDate startDate = convertToLocalDate(start);
        LocalDate endDate = convertToLocalDate(end);
        return new Event(descriptionEvent, startDate, endDate);
    }

    private void markIfNeeded(Task task, String[] details) throws NumberFormatException {
        if (Integer.parseInt(details[1].trim()) == 1) {
            task.markAsDone();
        }
    }

    private void createNewFile() throws DiboException {
        File f = new File(this.filePath);
        if (f.mkdir()) {
            System.out.println("Hi sir! We have just created a new text file "
                    + "for you to store your task list :D");
        } else {
            throw new DiboException("Sorry sir! We tried to add new text file "
                    + "for you to store your task list but was unable to do so.\n"
                    + "Please do us a favour and check the path name :D");
        }
    }

    private LocalDate convertToLocalDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, Storage.INPUT_FORMAT);
    }
}
