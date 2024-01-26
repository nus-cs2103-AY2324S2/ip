package services;

import exceptions.DukeException;
import tasks.Task;
import services.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;
/**
 * The Storage class is responsible for handling the loading and saving of tasks to a file.
 */
public class Storage {
    private static final String FILE_PATH = "./data/duke.txt";
    public Storage() {};

    /**
     * Loads tasks from the file at FILE_PATH and adds them to the provided TaskList.
     *
     * @param taskList The TaskList to which tasks are to be added.
     * @throws IOException If an error occurs during file reading.
     */
    public void loadTasks(TaskList taskList) throws IOException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = Parser.parseTaskFromString(taskString);
                // MONKEY FIX
                taskList.getTasks().add(task);
            }
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        } catch (DukeException e) {
            System.out.println("Error occurred when parsing file");
        }
    }
    /**
     * Saves tasks from the provided TaskList to the file at FILE_PATH.
     *
     * @param taskList The TaskList whose tasks are to be saved.
     */
    public void saveTasks(TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (Task task: taskList.getTasks()) {
                fileWriter.write(task.fileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occurred when writing to file");
        }
    }
}
