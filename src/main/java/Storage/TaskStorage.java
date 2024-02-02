package Storage;

import SnomExceptions.InvalidCommandIndexException;
import TaskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskStorage {

    /**
     * Saves the tasks to a .txt file
     */
    private void saveTask(TaskList lst) {
        File f = new File("data/tasks.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            f.getParentFile().mkdirs();
            saveTask(lst);

        }
        updateFile("data/tasks.txt", lst);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void updateFile (String filename, TaskList lst){
        try {
            for (int i = 0; i < lst.getCounter(); i++) {
                writeToFile(filename, lst.getTask(i) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (InvalidCommandIndexException e) {
            System.out.println("this should not happen");
        }
    }
}
