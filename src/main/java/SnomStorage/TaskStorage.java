package SnomStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import snomexceptions.InvalidCommandIndexException;
import snomtasklist.TaskList;

public class TaskStorage {

    /**
     * Saves the tasks to a .txt file
     */
    public void saveTask(TaskList lst) {
        File f = new File("data/tasks.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            f.getParentFile().mkdirs();
            saveTask(lst);

        }
        //System.out.println(f.getAbsolutePath());
        updateFile("data/tasks.txt", lst);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private void updateFile(String filename, TaskList lst) {
        try {
            for (int i = 1; i <= lst.getTaskNum(); i++) {
                writeToFile(filename, lst.getTaskAtIndex(i) + System.lineSeparator());
                //System.out.println(lst.getTask(i));
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (InvalidCommandIndexException e) {
            System.out.println("this should not happen4");
            System.out.println(e.getMessage());
        }
    }
}
