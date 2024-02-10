package dino;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dino.task.Task;


/**
 * Handles the loading and saving of tasks from/to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the constructor and returns a TaskList.
     *
     * @return The TaskList containing tasks loaded from the file.
     */
    public TaskList loadTasksFromFile() {
        TaskList taskList = new TaskList();
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                parseTaskData(taskData, taskList);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + " seems to not exist. TT Let me create one for you.");
        } catch (DinoException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    /**
     * Parses task data and adds the parsed task to the given task list.
     *
     * @param taskData The data representing a task.
     * @param taskList The TaskList to which the parsed task will be added.
     */
    private void parseTaskData(String taskData, TaskList taskList) throws DinoException {
        Task task = null;
        String[] parts = taskData.split("\\|");
        if (parts.length > 0) {
            String taskTypeString = parts[0].trim();
            switch (taskTypeString) {
            case "T":
                task = parseTodoTask(parts);
                break;
            case "D":
                task = parseDeadlineTask(parts);
                break;
            case "E":
                task = parseEventTask(parts);
                break;
            default:
                System.out.println("Unknown task type in file: " + taskTypeString);
                break;
            }
        }
        if (task != null) {
            taskList.addTask(task);
        } else {
            System.out.println("Error loading task from file. Skipping invalid task.");
        }
    }

    /**
     * Parses a todo task from the given parts and returns the corresponding Task.
     *
     * @param parts The parts of the todo task.
     * @return The parsed TodoTask.
     */
    private Task parseTodoTask(String[] parts) throws DinoException {
        return Parser.createTaskFromInput(Dino.TaskType.TODO, parts[2].trim());
    }

    /**
     * Parses a deadline task from the given parts and returns the corresponding Task.
     *
     * @param parts The parts of the deadline task.
     * @return The parsed DeadlineTask.
     */
    private Task parseDeadlineTask(String[] parts) throws DinoException {
        String[] deadlineParts = parts[3].split(" by: ");
        String deadlineDetails = parts[2].trim() + " /by " + Parser.parseStringToNum(deadlineParts[1]);
        return Parser.createTaskFromInput(Dino.TaskType.DEADLINE, deadlineDetails);
    }

    /**
     * Parses an event task from the given parts and returns the corresponding Task.
     *
     * @param parts The parts of the event task.
     * @return The parsed EventTask.
     */
    private Task parseEventTask(String[] parts) throws DinoException {
        String[] eventParts = parts[3].split("from:|to:");
        String eventDetails = parts[2].trim() + " /from " + Parser.parseStringToNum(eventParts[1])
                + " /to " + Parser.parseStringToNum(eventParts[2]);
        return Parser.createTaskFromInput(Dino.TaskType.EVENT, eventDetails);
    }

    /**
     * Saves the provided task list to the file specified in the constructor.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.println(task.toString());
            }
        }
    }
}
