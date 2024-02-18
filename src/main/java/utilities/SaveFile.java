package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * SaveFile that stores the user's current task list.
 */
public class SaveFile {
    /**
     * The file name of the save file.
     */
    private String fileName;

    /**
     * The directory path of where the save file is saved.
     */
    private String directoryName;

    /**
     * SaveFile class constructor.
     * @param fileName The file name of the save file.
     * @param directoryName The directory path of where the save file is saved.
     */
    public SaveFile(String fileName, String directoryName) {
        this.fileName = fileName;
        this.directoryName = directoryName;
    }

    /**
     * Method to create directory path if it does not already exist.
     */
    public void createDirectory() {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            if (directory.mkdir()) {
            } else {
                System.err.println("Error creating directory");
            }
        }
    }

    /**
     * Method to create the save file if it does not already exist.
     */
    public void createFile() {
        File file = new File(directoryName, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error generating task list");
            }
        }
    }

    /**
     * Writes the task list into the save file.
     * @param taskList The task list that is to be saved.
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.directoryName + "/" + this.fileName);
            for (Task t : taskList.getList()) {
                fw.write(t.toFileString());
                fw.write((System.lineSeparator()));
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Error writing task list to file: " + e.getMessage());
        }
    }

    /**
     * Reads the task list from the save file.
     * @return The task list as an array list.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            File f = new File(this.directoryName + "/" + this.fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                boolean isDone;

                if (data.split("[|]")[1].equals("0")) {
                    isDone = false;
                } else {
                    isDone = true;
                }

                if (data.startsWith("T")) {
                    String description = data.split("[|]")[2];
                    Todo newTodo = new Todo(description, isDone);
                    taskList.add(newTodo);
                } else if (data.startsWith("D")) {
                    String description = data.split("[|]")[2];
                    String by = data.split("[|]")[3];
                    Deadline newDeadline = new Deadline(description, isDone, by);
                    taskList.add(newDeadline);
                } else if (data.startsWith("E")) {
                    String description = data.split("[|]")[2];
                    String from = data.split("[|]")[3];
                    String to = data.split("[|]")[4];
                    Event newEvent = new Event(description, isDone, from, to);
                    taskList.add(newEvent);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
        return taskList;
    }
}
