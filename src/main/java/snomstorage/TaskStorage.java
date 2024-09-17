package snomstorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import snomtask.Task;
import snomtasklist.TaskList;

/**
 * TaskStorage saves the tasks onto a local directory
 */
public class TaskStorage {

    /**
     * Reads the tasks previously entered by the user into TaskList.
     * If there is no TaskList, create a new directory to save the
     *         tasks later.
     * @param lst is the TaskList to save the tasks to.
     */
    public void readTasks(TaskList lst) {
        File f = new File("data/tasks.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            f.getParentFile().mkdirs();
            readTasks(lst);
            return;
        }
        loadTasks(lst, f);
    }

    /**
     * Adds the tasks previously entered by the user into TaskList.
     * @param lst is the TaskList to save the tasks to.
     */
    public void loadTasks(TaskList lst, File tasks) {
        try {
            Scanner input = new Scanner(tasks);
            while (input.hasNext()) {
                Task t = Task.convertFromStringToTask(input.nextLine());
                lst.addTask(t);
            }
        } catch (FileNotFoundException e) {
            assert false : "File should have been created";
        }

    }

    /**
     * Saves the tasks to a .txt file
     */
    public void saveTasks(TaskList lst) {
        File f = new File("data/tasks.txt");
        assert f.exists() : "This file should have been created";
        updateFile("data/tasks.txt", lst);
    }

    private static void writeToFile(String filePath, TaskList lst) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(lst.getTasks());
        fw.close();
    }

    private void updateFile(String filename, TaskList lst) {
        try {
            writeToFile(filename, lst);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


}
