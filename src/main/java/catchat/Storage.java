package catchat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final String FOLDER_PATH = "./tasklist";
    private static final String TASKLIST_PATH = FOLDER_PATH + "/tasklist.txt";

    private String taskType;
    private String taskStatus;
    private String taskDescription;

    private final Path taskListPath = Paths.get(TASKLIST_PATH);
    private final Path folderPath = Paths.get(FOLDER_PATH);
    private final ArrayList<Task> taskList;

    /**
     * Constructor for Storage
     *
     * @param taskList
     */
    public Storage(ArrayList<Task> taskList) {
        this.taskList = taskList;
        createTaskList();
    }

    /**
     * Loads taskList from file
     */
    public void loadTaskList() {
        try {
            // Clear existing tasks before loading
            this.taskList.clear();

            // Load tasks from file
            ArrayList<String> taskListFromFile = new ArrayList<>(Files.readAllLines(taskListPath));

            // For each task in the file, add it to the taskList
            for (String task : taskListFromFile) {
                String[] taskParts = task.split(" \\| ", 3);
                taskType = taskParts[0];
                taskStatus = taskParts[1];
                taskDescription = taskParts[2];

                switch (taskType) {
                case "T":
                    handleTodo();
                    break;
                case "D":
                    handleDeadline();
                    break;
                case "E":
                    handleEvent();
                    break;
                default:
                    System.out.println("Unrecognized task type: " + task);
                    throw new IOException("Error loading data from file: unrecognized task type");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Handles Todo task
     */
    public void handleTodo() {
        Todo newTodo = new Todo(taskDescription);
        if (taskStatus.equals("done")) {
            newTodo.markDone();
        }
        this.taskList.add(newTodo);
    }

    /**
     * Handles Deadline task
     */
    public void handleDeadline() {
        String[] deadlineParts = taskDescription.split(" \\(by: ", 2);
        String deadlineDescription = deadlineParts[0];
        String deadlineByDateTime = deadlineParts[1]
                .substring(0, deadlineParts[1].length() - 1);

        Deadline newDeadline = new Deadline(deadlineDescription, deadlineByDateTime);

        if (taskStatus.equals("done")) {
            newDeadline.markDone();
        }
        this.taskList.add(newDeadline);
    }

    /**
     * Handles Event task
     */
    public void handleEvent() {
        String[] eventParts = taskDescription.split(" \\(from: ", 2);
        String eventDescription = eventParts[0];

        String eventAt = eventParts[1].substring(0, eventParts[1].length() - 1);
        String[] eventAtParts = eventAt.split(", to: ", 2);

        String eventFrom = eventAtParts[0];
        String eventTo = eventAtParts[1];

        Events newEvent = new Events(eventDescription, eventFrom, eventTo);
        if (taskStatus.equals("done")) {
            newEvent.markDone();
        }
        this.taskList.add(newEvent);
    }

    /**
     * Saves taskList to file
     */
    public void saveTaskListToFile() {
        try {
            ArrayList<String> newTaskList = new ArrayList<>();

            for (Task task : this.taskList) {
                newTaskList.add(task.toString());
            }

            Files.write(taskListPath, newTaskList);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    /**
     * Creates taskList and tasklist.txt if they do not exist
     */
    private void createTaskList() {
        try {
            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            if (Files.notExists(taskListPath)) {
                Files.createFile(taskListPath);
            }
        } catch (IOException e) {
            System.out.println("Error creating tasklist: " + e.getMessage());
        }
    }

    public String getDescription(String taskType, String taskDescription) {
        switch (taskType) {
        case "T":
            return getTodoDescription(taskDescription);
        case "D":
            return getDeadlineDescription(taskDescription);
        case "E":
            return getEventDescription(taskDescription);
        default:
            return "Unknown task type";
        }
    }

    private String getTodoDescription(String taskDescription) {
        return taskDescription;
    }

    private String getDeadlineDescription(String taskDescription) {
        String[] deadlineParts = taskDescription.split(" \\(by: ", 2);
        return deadlineParts[0];
    }

    private String getEventDescription(String taskDescription) {
        String[] eventParts = taskDescription.split(" \\(from: ", 2);
        return eventParts[0];
    }
}
