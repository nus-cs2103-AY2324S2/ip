package luke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks from and to a file.
 */
public class Storage {
    private final String filepath;
    private final File file;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath the file path for storing tasks
     */
    Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Loads tasks from the file.
     *
     * @return an ArrayList containing the tasks loaded from the file
     * @throws LukeException if an error occurs during file loading
     */
    public ArrayList<String> loadFile() throws LukeException {
        ArrayList<String> taskList = new ArrayList<>();
        try {
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to save file: " + e.getMessage());
        }

        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.isEmpty()) {
                    taskList.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
        return taskList;
    }

    /**
     * Saves tasks to the file.
     *
     * @param taskListObject the TaskList object containing the tasks to be saved
     * @return message
     */
    public String saveFile(TaskList taskListObject) {
        try {
            ArrayList<Task> taskList = taskListObject.getTaskList();
            FileWriter writer = new FileWriter(filepath);
            for (Task task : taskList) {
                writer.write(task.toString());
                writer.write("\n");
            }
            writer.close();
            return "File saved. Hope to see you again soon!";
        } catch (IOException e) {
            return "An error occurred while saving." + e.getMessage();
        }
    }
}
