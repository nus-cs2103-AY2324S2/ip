package scribbles.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import scribbles.task.Deadline;
import scribbles.task.Event;
import scribbles.task.Task;
import scribbles.task.Todo;
import scribbles.tasklist.TaskList;

/**
 * This class deals with creating file for storage, loading tasks from the file and saving task to the file.
 */
public class Storage {
    private String filePath;
    private TaskList taskList;

    /**
     * Constructs a Storage object with the given file path to store information in the hard disk and task list
     * to load information into chatbot for the user.
     *
     * @param filePath File path to file which stores tasks.
     * @param taskList Task list to store tasks.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
        createFile();

        try {
            this.loadFileData(this.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File " + this.filePath + " not found. Unable to load data from file.");
        }
    }

    /**
     * Checks if file path exists.
     * Creates file path if it does not exist.
     */
    public void createFile() {
        File f = new File(this.filePath);

        try {
            File directory = f.getParentFile();
            if (!directory.exists()) {
                boolean hasCreatedDirectory = directory.mkdir();
                if (!hasCreatedDirectory) {
                    System.out.println("Failed to create directory for file.\n");
                }
            }

            if (!f.exists()) {
                boolean hasCreatedFile = f.createNewFile();
                if (!hasCreatedFile) {
                    System.out.println("Failed to create file.\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /**
     * Loads data from file to task list in Scribbles.
     *
     * @param taskList List to load data into
     * @throws FileNotFoundException If data file does not exist
     */
    public void loadFileData(TaskList taskList) throws FileNotFoundException {
        File f = new File(this.filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            String delimiter = "\\s*\\|\\s*";

            while (line != null) {
                String[] tokens = line.split(delimiter);
                String typeOfTask = tokens[0].trim();
                int completed = Integer.parseInt(tokens[1].trim());
                boolean isCompleted = (completed == 1 ? true : false);
                String description = tokens[2].trim();

                switch(typeOfTask) {
                case "T":
                    taskList.addTask(new Todo(description, isCompleted));
                    break;
                case "D":
                    String deadlineString = tokens[3].trim();
                    try {
                        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime deadline = LocalDateTime.parse(deadlineString, dateTimeFormat);
                        taskList.addTask(new Deadline(description, isCompleted, deadline));
                    } catch (DateTimeParseException e) {
                        System.out.println("Your deadline, \"" + description + "\" stored in file has incorrect "
                                + "date/time format and cannot be loaded into task list. ");
                    }
                    break;
                case "E":
                    String startString = tokens[3].trim();
                    String endString = tokens[4].trim();
                    try {
                        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime start = LocalDateTime.parse(startString, dateTimeFormat);
                        LocalDateTime end = LocalDateTime.parse(endString, dateTimeFormat);
                        taskList.addTask(new Event(description, isCompleted, start, end));
                    } catch (DateTimeParseException e) {
                        System.out.println("Your event, \"" + description + "\" stored in file has incorrect "
                                + "date/time format and cannot be loaded into task list. ");
                    }
                    break;
                default:
                    System.out.println("Invalid task type \"" + typeOfTask + "\" was found in file.");
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Uh oh! looks like the data in your file is corrupted and cannot be read!\n"
                    + "Please verify data in the file to proceed. Proceeding without verification may cause\n"
                    + "current data to disappear.\n");
        }
    }

    /**
     * Saves data from task list into file.
     *
     * @param taskList List that contains data to save into file
     * @throws FileNotFoundException If file does not exist
     */
    public void saveFileData(TaskList taskList) throws FileNotFoundException {
        File f = new File(this.filePath);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task instanceof Todo) {
                    writer.write("T | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription());
                }
                if (task instanceof Deadline) {
                    writer.write("D | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription()
                            + " | " + ((Deadline) task).getByString());
                }
                if (task instanceof Event) {
                    writer.write("E | " + (task.isCompleted() ? "1" : "0") + " | " + task.getDescription()
                            + " | " + ((Event) task).getStartString() + " | " + ((Event) task).getEndString());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
