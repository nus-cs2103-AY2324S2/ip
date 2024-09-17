package chatteroo.storage;

import chatteroo.ChatterooException;
import chatteroo.parser.Parser;
import chatteroo.tasks.Task;
import chatteroo.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the storage of tasks in a file.
 */
public class Storage {
    private static String DIRECTORY = "data";
    private static String FILE_NAME = "chatteroo.txt";
    private static File listFile = new File(Paths.get(DIRECTORY, FILE_NAME).toString());

    /**
     * Constructor for the Storage class.
     * @throws IOException If an I/O error occurs.
     */
    public Storage() throws IOException {
        Path directoryPath = Paths.get(DIRECTORY);
        if (!Files.exists(directoryPath)) {
            Files.createDirectory(directoryPath);
        }
        if (!listFile.exists()) {
            try {
                listFile.createNewFile();
            } catch (IOException e) {
                throw new IOException("ChatterOOHNOO! Chatteroo can't create a new file!");
            }
        }
    }

    /**
     * Loads the tasks from the file.
     * @return The list of tasks.
     * @throws ChatterooException If the file cannot be read.
     */
    public ArrayList<Task> loadTasks() throws ChatterooException {
        ArrayList<Task> listStore = new ArrayList<>();
        try {
            if (listFile.exists() && listFile.length() > 0) {
                Scanner sc = new Scanner(listFile);
                while (sc.hasNextLine()) {
                    String input = sc.nextLine();
                    Task newTask = Parser.parseFileTasks(input);
                    listStore.add(newTask);
                }
                sc.close();
            }
        } catch (IOException e) {
            throw new ChatterooException("ChatterOOHNOO! Chatteroo can't create a new file!");
        } catch (Exception e) {
            throw new ChatterooException("ChatterOOHNOO! Chatteroo couldn't retrieve your tasks :-(");
        }
        return listStore;
    }

    /**
     * Saves the tasks to the file.
     * @param listStore The list of tasks.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(TaskList listStore) throws IOException{
        FileWriter fw = new FileWriter(Paths.get(DIRECTORY, FILE_NAME).toString());
        for (int i = 1; i <= listStore.getTaskListSize(); i++) {
            Task currTask = listStore.getTask(i);
            String saveTaskString = currTask.formatTaskForFile(currTask);
            fw.write(saveTaskString);
        }
        fw.close();
    }

}
