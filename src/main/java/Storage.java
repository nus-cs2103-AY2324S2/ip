import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list to store the tasks.
 */
public class Storage {
    private  ArrayList<Task> tasks; // task list
    private static final String FILE_PATH = "./data/Tes.txt"; // path of the file
    /** Format of the date and time in the command received */
    private static final DateTimeFormatter FORMATTER_RECEIVE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    /** Format of the date and time in database */
    private static final DateTimeFormatter FORMATTER_STORE = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    /**
     * Constructs a list to store the task.
     * Assumes the maximum size of the task list to be 100.
     * If there is an existing task list, load it from the local disk, else create a new one.
     */
    public Storage() {
        this.tasks = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Stores the to-do task into the task list.
     *
     * @param task Description of task to be stored.
     */
    public void storeToDo(String task) {
        ToDo newToDo = new ToDo(task);
        this.tasks.add(newToDo);
        this.saveToFile();
    }

    /**
     * Stores the task with deadline into the task list.
     *
     * @param task Description of task to be stored.
     * @param by Deadline of the task.
     */
    public void storeDeadline(String task, String by) {
        LocalDateTime by1 = LocalDateTime.parse(by, FORMATTER_RECEIVE);
        Deadline newDeadline = new Deadline(task, by1);
        this.tasks.add(newDeadline);
        this.saveToFile();
    }

    /**
     * Stores the task with a designated period into the task list.
     *
     * @param task Description of task to be stored.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public void storeEvent(String task, String from, String to) {
        LocalDateTime from1 = LocalDateTime.parse(from, FORMATTER_RECEIVE);
        LocalDateTime to1 = LocalDateTime.parse(to, FORMATTER_RECEIVE);
        Event newEvent = new Event(task, from1, to1);
        this.tasks.add(newEvent);
        this.saveToFile();
    }

    /**
     * Gets task description.
     *
     * @param index Index of task in the list.
     * @return The task description.
     */
    public String getTaskDescription(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Gets the size of list.
     *
     * @return The size of list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks the task as done.
     *
     * @param index Index of task in the list.
     */
    public void mark(int index) {
        this.tasks.get(index).mark();
        this.saveToFile();
    }

    /**
     * Unmarks the task.
     *
     * @param index Index of task in the list.
     */
    public void unmark(int index) {
        this.tasks.get(index).unmark();
        this.saveToFile();
    }

    /**
     * Deletes the item in tasks with the respective index.
     *
     * @param index Index of task in the list.
     */
    public void delete(int index) {
        this.tasks.remove(index);
        this.saveToFile();
    }

    /**
     * Saves the recorded tasks into the local disk.
     * If there is no existing directory mentioned in the path, create one.
     */
    private void saveToFile() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            PrintWriter writer = new PrintWriter(file);

            for (Task task : tasks) {
                writer.println(task.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Loads existing task list from the local disk if there is one.
     * If no, a new empty task list is used.
     */
    private void loadFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return;
            }
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    if (line.contains("from:")) {
                        String[] split = line.split(" \\| ");
                        String temp = split[3].substring(6);
                        String[] time = temp.split(" to: ");
                        String from = time[0];
                        String to = time[1];
                        String from1 = LocalDateTime.parse(from, FORMATTER_STORE).format(FORMATTER_RECEIVE);
                        String to1 = LocalDateTime.parse(to, FORMATTER_STORE).format(FORMATTER_RECEIVE);
                        this.storeEvent(split[2], from1, to1);
                        if (split[1].equals("X")) {
                            this.mark(this.tasks.size() - 1);
                        }
                    } else if (line.contains("by:")) {
                        String[] split = line.split(" \\| ");
                        String by = split[3].substring(4);
                        String by1 = LocalDateTime.parse(by, FORMATTER_STORE).format(FORMATTER_RECEIVE);
                        this.storeDeadline(split[2], by1);
                        if (split[1].equals("X")) {
                            this.mark(this.tasks.size() - 1);
                        }
                    } else {
                        String[] split = line.split(" \\| ");
                        this.storeToDo(split[2]);
                        if (split[1].equals("X")) {
                            this.mark(this.tasks.size() - 1);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error printing task from file: " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error printing task from file: " + e.getMessage());
        }
    }
}
