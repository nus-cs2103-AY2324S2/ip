package dino.command;

import dino.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks from/to a file.
 */
public class Storage {
    private String filePath;
    private Task task;

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
            Parser parser = new Parser(taskList, new Ui(), fileScanner);

            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                String[] parts = taskData.split("\\|");

                if (parts.length > 0) {
                    String taskTypeString = parts[0].trim();
                    switch (taskTypeString) {
                        case "T":
                            task = parser.createTaskFromInput(Dino.TaskType.TODO, parts[2].trim());
                            break;
                        case "D":
                            String[] deadlineParts = parts[3].split(" by: ");
                            String deadlineDetails = parts[2].trim() + " /by " + parser.parseStringToNum(deadlineParts[1]);
                            task = parser.createTaskFromInput(Dino.TaskType.DEADLINE, deadlineDetails);
                            break;
                        case "E":
                            String[] eventParts = parts[3].split("from:|to:");
                            String eventDetails = parts[2].trim() + " /from " + parser.parseStringToNum(eventParts[1]) +
                                    " /to " + parser.parseStringToNum(eventParts[2]);
                            task = parser.createTaskFromInput(Dino.TaskType.EVENT, eventDetails);
                            break;
                        default:
                            System.out.println("Unknown task type in file: " + taskTypeString);
                    }
                }

                if (task != null) {
                    taskList.addTask(task);
                } else {
                    System.out.println("Error loading task from file. Skipping invalid task.");
                }
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
     * Saves the provided task list to the file specified in the constructor.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) {
        try {
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
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
