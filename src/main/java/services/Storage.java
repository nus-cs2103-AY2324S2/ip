package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.DukeException;
import services.parser.Parser;
import tasks.Task;

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
        assert taskList != null : "TaskList provided to loadTasks cannot be null";
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                assert file.exists() : "File should exist after creation";
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskString = scanner.nextLine();
                Task task = Parser.parseTaskFromString(taskString);
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
        assert taskList != null : "TaskList provided to saveTasks cannot be null";
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                assert file.exists() : "File should exist after creation";
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
