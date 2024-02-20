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
import dibo.task.DoAfter;
import dibo.task.Event;
import dibo.task.Task;
import dibo.task.ToDo;

/**
 * The Storage class represents the entity which interacts with the hardware, text file.
 * It is responsible for loading thr data from the text file
 * as well as saving the changes to it.
 */
public class Storage {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final String filePath;

    /**
     * Constructs a new Storage object.
     *
     * @param filePath The relative file path to the text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the text file and returns an ArrayList.
     *
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
                case "do-after":
                    task = loadDoAfter(details);
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
            throw new DiboException("Oh no sir! We are unable to update the task lists.");
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

    private DoAfter loadDoAfter(String[] details) throws IndexOutOfBoundsException {
        String descriptionDoAfter = details[2];
        String after = details[3].trim();
        LocalDate afterDate = convertToLocalDate(after);
        return new DoAfter(descriptionDoAfter, afterDate);
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
        try {
            boolean fileCreated = f.createNewFile();
            if (!fileCreated) {
                throw new DiboException("The file at " + this.filePath + " already exists or cannot be created.");
            }
        } catch (IOException io) {
            throw new DiboException("An error occurred while trying to create the file at "
                    + this.filePath + ".\nPlease check the path name and your file system permissions.");
        }
    }

    private LocalDate convertToLocalDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, Storage.INPUT_FORMAT);
    }
}
