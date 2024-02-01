package sleepy.taskstorage;

import sleepy.tasks.Task;
import sleepy.tools.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File hardDiskFile;
    public Storage(String filePath) {
        try {;
            hardDiskFile = new File(filePath);
            hardDiskFile.createNewFile();
        } catch (IOException i) {
            Ui.printLine("Sleepy encountered a serious error! Please restart the application :(");
        }
    }

    /**
     * Returns the tasks read from the file as an ArrayList of strings.
     *
     * @return Tasks read from the file.
     */
    public ArrayList<String> readFile() {
        ArrayList<String> readTasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(hardDiskFile);
            while (scanner.hasNextLine()) {
                readTasks.add(scanner.nextLine());
            }
        } catch (FileNotFoundException f) {
            Ui.printLine("Sleepy encountered a bug and could not recover your data!");
        }
        return readTasks;
    }

    /**
     * Saves the tasks in the hard disk.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(hardDiskFile, false);
            for (Task task : tasks) {
                fileWriter.write(task.getRawDescription() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException i) {
            Ui.printLine("Sleepy couldn't save your data! Please restart the application :(");
        }
    }
}
