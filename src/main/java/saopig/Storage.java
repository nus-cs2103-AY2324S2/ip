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
     * Saves the task list to the file path specified in FILE_PATH.
     * @param taskList TaskList object to store the list of tasks.
     */
    public void saveTaskList(TaskList taskList) {
        try {
            if (!new File(FILE_DIRECTORY).exists()) {
                new File(FILE_DIRECTORY).mkdirs();
                System.out.println("Creating new directory");
            }
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                System.out.println("Creating new file");
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            if (taskList.getSize() == 0) {
                fileWriter.write("");
            } else {
                for (Task task : taskList.getTasks()) {
                    String taskType = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
                    String isDone = task.getIsDoneState() ? "1" : "0";
                    String description = task.getDescription();
                    String divideSymbol = " %&///&% ";
                    if (task instanceof Deadline) {
                        description += divideSymbol
                                + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Deadline) task).getBy());
                    } else if (task instanceof Event) {
                        description += divideSymbol
                                + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Event) task).getStartTime())
                                + divideSymbol
                                + DateTimeFormatter.ofPattern(TIME_PATTERN).format(((Event) task).getEndTime());
                    }
                    fileWriter.write(taskType + divideSymbol + isDone + divideSymbol + description + "\n");
                }
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
                        + "Oh dear, it looks like there are no tasks stored yet!\n "
                        + "But that's alright.\n "
                        + "It gives us a chance to start fresh and dream up some new plans.\n "
                        + "Whenever you're ready to add tasks, I'll be right here to assist you.\n "
                        + "Let's make it a magical journey together!");
                return taskList;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String divideSymbol = " %&///&% ";
                String[] splitInput = input.split(divideSymbol);
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
                if (splitInput[1].equals("1")) {
                    taskList.getTask(taskList.getSize() - 1).markAsDone();
                }
            }
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
}
