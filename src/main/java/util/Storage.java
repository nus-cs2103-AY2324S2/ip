package util;

import exceptions.ChatBotException;
import tasks.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Storage class is responsible for saving tasks to a file and loading tasks from a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/duke/.txt";
    public Storage() {};

    /**
     * Saves the tasks from the task list to a file.
     *
     * @param taskList The TaskList containing tasks to be saved.
     */
    public void saveToFile(TaskList taskList) {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            for (Task task: taskList.getTasks()) {
                fw.write(task.toStringForFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! There was an error saving to file.");
        }
    }

    /**
     * Loads tasks from a file into the task list.
     *
     * @param taskList The TaskList where loaded tasks will be stored.
     * @throws IOException       If an I/O error occurs.
     * @throws ChatBotException If there is an error parsing the file.
     */
    public void loadFromFile(TaskList taskList) throws IOException {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = Parser.parseTasksFromFile(taskString);
                taskList.getTasks().add(task);
            }
        } catch (IOException e) {
            System.out.println("Oops! There was an error loading the file.");
        } catch (ChatBotException e) {
            System.out.println("Oops! There was an error parsing the file.");
        }
    }
}

