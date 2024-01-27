package storage;

import exceptions.DukeException;
import tasks.*;
import static constants.FilePaths.RELATIVE_DATA_DIRECTORY_PATH;
import static constants.FilePaths.RELATIVE_OUTPUT_TXT_FILE_PATH;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    public Storage() throws DukeException{
        init();
    }

    // initialisation of data dir and output file
    public TaskList load() throws DukeException{
        return initTaskList(RELATIVE_OUTPUT_TXT_FILE_PATH);
    }

    public void init() throws DukeException {
        File dataDir = new File(RELATIVE_DATA_DIRECTORY_PATH);
        File outputTxt = new File(RELATIVE_OUTPUT_TXT_FILE_PATH);
        createOutputDirectoryAndFile(dataDir, outputTxt);
    }

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public static void createOutputDirectoryAndFile(File dataDir, File outputTxt) throws DukeException {
        try {
            if (!dataDir.isDirectory()) {
                dataDir.mkdirs();
            }
            if (!outputTxt.exists()) {
                outputTxt.createNewFile();
            }
        } catch (SecurityException e) {
            throw new DukeException(e.toString());
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }

    public static TaskList initTaskList(String filePath) throws DukeException {
        TaskList list = new TaskList();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                String[] parts = scanner.nextLine().split(" \\| ");
                switch (parts[0]) {
                case "T":
                    list.add(new Todo(parts[1], parts[2]));
                    break;
                case "D":
                    list.add(new Deadline(parts[1], parts[2], parts[3]));
                    break;
                case "E":
                    list.add(new Event(parts[1], parts[2], parts[3], parts[4]));
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("Error reading from file: " + e.toString());
        }

        return list;
    }

    public void writeToFile(String filePath, ArrayList<Task> taskList) throws DukeException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.textFormattedOutput() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("Error writing to file: " + e.toString());
        }
    }

}
