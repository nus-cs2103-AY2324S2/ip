package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Loads and saves the list of tasks to a file.
 */
public class Storage {
    private static int MAX_ATTEMPT = 2;
    private String filepath;

    /**
     * Returns an instance of Storage that will load/save to the given filepath.
     *
     * @param filepath The filepath of the file to load/saves the tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Checks if filepath exist, if not creates one from the current directory.
     * Maximum attempt of 2, else the program will exit with error.
     */
    public void checkForFilePath() {
        int currentAttempt = 0;
        File file;

        while (++currentAttempt <= MAX_ATTEMPT) {
            try {
                Ui.showLine();
                String counter = "Startup Attempt #" + currentAttempt + "/" + MAX_ATTEMPT + ":";
                System.out.println(counter);

                file = new File(filepath);
                if (file.createNewFile()) {
                    String s1 = "tasks.txt does not exist.";
                    String s2 = "tasks.txt successfully created.";
                    System.out.println(s1);
                    System.out.println(s2);

                } else {
                    String s = "tasks.txt already exist.";
                    System.out.println(s);
                }
                break;

            } catch (IOException e) {
                System.out.println("IOException occured: " + e.getMessage());

                File dir = new File("./data");
                boolean isDirectoryCreated = dir.mkdir();
                if (isDirectoryCreated) {
                    String s = "Directory ./data created.";
                    System.out.println(s);
                }

            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * Returns the list of tasks loaded from the storage file.
     *
     * @return The list of tasks loaded in a LinkedList.
     */
    public LinkedList<Task> loadData() {
        checkForFilePath();
        LinkedList<Task> tasks = new LinkedList<>();

        try {
            Scanner fileScanner = new Scanner(new File(filepath));
            boolean hasError = false;

            while (fileScanner.hasNext()) {
                String[] taskFields = fileScanner.nextLine().split(" \\| ");
                String taskType = taskFields[0];
                boolean isDone = Integer.parseInt(taskFields[1]) == 1 ? true : false;

                if (taskType.equals("T")) {
                    ToDo td = new ToDo(isDone, taskFields[2]);
                    tasks.add(td);

                } else if (taskType.equals("D")) {
                    try {
                        Deadline d = new Deadline(isDone, taskFields[2], taskFields[3]);
                        tasks.add(d);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage() + "\n");
                        hasError = true;
                    }

                } else if (taskType.equals("E")) {
                    try {
                        Event e = new Event(isDone, taskFields[2], taskFields[3]);
                        tasks.add(e);

                    } catch (DukeException e) {
                        System.out.println(e.getMessage() + "\n");
                        hasError = true;
                    }
                }
            }
            fileScanner.close();

            if (hasError) {
                Ui.showLine();

            } else {
                String s = "tasks.txt loaded without error.";
                System.out.println(s);
                Ui.showLine();
            }

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException occured: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the storage file.
     *
     * @param tasks The list of tasks to save.
     */
    public void saveDataAndExit(TaskList tasks) {
        try {
            System.out.println("Saving data ...");

            File file = new File(filepath);
            FileWriter fw = new FileWriter(file, false);

            LinkedList<Task> taskList = tasks.getList();
            for (Task t : taskList) {
                fw.write(t.toData());
                fw.write(System.lineSeparator());
            }

            fw.close();
            System.out.println("Data saved successfully. :)");

        } catch (IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }
}
