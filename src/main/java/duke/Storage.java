package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the part of the program taht handles the file management.
 */
public class Storage {
    private final File file;

    /**
     * Initialize the file using the given folder and file name. If there is an existing file, just use it.
     * If the folder or file does not exist, create them.
     *
     * @param fileFolder The directory of the file
     * @param fileName   Name of the file
     */
    Storage(String fileFolder, String fileName) {
        File directory = new File(fileFolder);
        if (!directory.exists()) {
            boolean isDirMade = directory.mkdirs();
            if (!isDirMade) {
                System.out.println("Oops! Something is wrong with directory creation!");
            }
        }
        file = new File(fileFolder + "/" + fileName);
        if (!file.exists()) {
            try {
                boolean isCreated = file.createNewFile();
                if (!isCreated) {
                    System.out.println("Oops! Something is wrong with file creation!");
                }
            } catch (IOException e) {
                System.out.println("Oops! Something is wrong with file creation!");
            }
        }
    }

    /**
     * Loads the tasks from an existing file, and sends the tasks to the initialization stage of the program.
     *
     * @return An ArrayList of the lines in the file
     */
    ArrayList<String> load() {
        ArrayList<String> result = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                result.add(line);
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("Oops! No file in the directory!");
            return result;
        }
    }

    /**
     * Rewrites a certain line in the file using the latest information of the task.
     *
     * @param num  the index of the task that needs to be updated
     * @param task The task with latest updated information
     */
    public void editLine(int num, Task task) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                } else {
                    lines.add(task.taskToLine());
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong with the file management!");
        }
    }

    /**
     * Deletes a line from the file if the task on that line is deleted.
     *
     * @param num The index of the task to be deleted
     */
    public void deleteLine(int num) {
        try {
            int i = 1;
            ArrayList<String> lines = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String newLine = sc.nextLine();
                if (i != num) {
                    lines.add(newLine);
                }
                i++;
            }
            FileWriter fw = new FileWriter(file);
            for (String line : lines) {
                fw.write(line + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! Something is wrong with the file management!");
        }
    }

    /**
     * Adds a new task to the file when a new task is created.
     *
     * @param task The new task added
     */
    public void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(task.taskToLine() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops! unable to write to the file!");
        }
    }
}
