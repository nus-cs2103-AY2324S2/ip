package saopig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.Task;
import saopig.task.TaskList;
import saopig.task.Todo;



/**
 * Storage class handles the loading and saving of the task list.
 */
public class Storage {
    private static final String DIVIDE_SYMBOL = " %&///&% ";
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor for Storage class.
     * @param ui Ui object to handle printing of messages.
     * @param taskList TaskList object to store the list of tasks.
     */
    public Storage(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;

    }
    /**
     * Checks if the directory exists and creates a new directory if it does not exist.
     */
    public void checkAndCreateDirectory() {
        if (!new File(FILE_DIRECTORY).exists()) {
            new File(FILE_DIRECTORY).mkdirs();
            System.out.println("Creating new directory");
        }
    }

    /**
     * Checks if the file exists and creates a new file if it does not exist.
     * @param file File object to check and create.
     * @throws IOException If an I/O error occurs.
     */
    public void checkAndCreateFile(File file) throws IOException {
        if (!file.exists()) {
            System.out.println("Creating new file");
            file.createNewFile();
        }
    }
    /**
     * Checks the type of the task and returns the corresponding string.
     * @param task Task object to check the type.
     * @return String representing the type of the task.
     */
    public String checkTaskType(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }
    /**
     * Returns the description of the task with the time if the task is a Deadline or Event.
     * @param task Task object to get the description.
     * @return String representing the description of the task.
     */
    public String getDescription(Task task) {
        String description = task.getDescription();
        if (task instanceof Deadline) {
            description += DIVIDE_SYMBOL
                    + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            description += DIVIDE_SYMBOL
                    + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Event) task).getStartTime())
                    + DIVIDE_SYMBOL
                    + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Event) task).getEndTime());
        }
        return description;
    }

    /**
     * Saves the task list to the file path specified in FILE_PATH.
     * @param taskList TaskList object to store the list of tasks.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            checkAndCreateDirectory();
            File file = new File(FILE_PATH);
            checkAndCreateFile(file);
            FileWriter fileWriter = new FileWriter(file);
            if (taskList.getSize() == 0) {
                fileWriter.write("");
                fileWriter.close();
                return;
            }
            for (Task task : taskList.getTasks()) {
                String taskType = checkTaskType(task);
                String isDone = task.getIsDoneState() ? "1" : "0";
                String description = getDescription(task);
                fileWriter.write(taskType + DIVIDE_SYMBOL + isDone + DIVIDE_SYMBOL + description + "\n");
            }
            fileWriter.close();
        } catch (IOException | DateTimeException e) {
            ui.printMessage("\n"
                    + "Oh no! I'm sorry, but I couldn't save your tasks.\n "
                    + "Please try again, or type 'bye' to exit.");
        }
    }

    /**
     * Loads the task list from the file path specified in FILE_PATH.
     * @param taskList TaskList object to store the list of tasks.
     * @return TaskList object with the tasks loaded from the file.
     */
    public TaskList loadTaskList(TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                ui.printMessage("\n"
                        + "Oh dear, it looks like there are no tasks stored yet!\n ");
                return taskList;
            }
            Scanner scanner = new Scanner(file);
            scanAndLoadTaskList(taskList, scanner);
            scanner.close();
            ui.printMessage("Successfully loaded your previous task list!\n "
                    + "Now you have " + taskList.getSize() + " tasks in the list.\n");
        } catch (IOException | DateTimeParseException e) {
            ui.printMessage("\n"
                    + "Oh no! I'm sorry, but I couldn't load your tasks.\n "
                    + "Please try again, or type 'bye' to exit.");
        }
        return taskList;
    }

    private static void scanAndLoadTaskList(TaskList taskList, Scanner scanner) {
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(DIVIDE_SYMBOL);
            loadTaskByType(taskList, splitInput);
            if (splitInput[1].equals("1")) {
                taskList.getTask(taskList.getSize() - 1).markAsDone();
            }
        }
    }

    private static void loadTaskByType(TaskList taskList, String[] splitInput) {
        switch (splitInput[0]) {
        case "T":
            taskList.getTasks().add(new Todo(splitInput[2]));
            break;
        case "D":
            LocalDateTime deadlineDateTime = LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER);
            taskList.getTasks().add(new Deadline(splitInput[2], deadlineDateTime));
            break;
        case "E":
            LocalDateTime fromDateTime = LocalDateTime.parse(splitInput[3], DATE_TIME_FORMATTER);
            LocalDateTime toDateTime = LocalDateTime.parse(splitInput[4], DATE_TIME_FORMATTER);
            taskList.getTasks().add(new Event(splitInput[2], fromDateTime, toDateTime));
            break;
        default:
            break;
        }
    }
}
