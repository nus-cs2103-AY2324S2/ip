package luke;

import luke.exception.DateException;
import luke.exception.FileException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.TaskList;
import luke.task.Todo;
import luke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the tasks into a file and reading the tasks from the file.
 */
public class Storage {
    private File file;
    private Ui ui;

    /**
     * Constructor for Storage that initializes the file and Ui, and creates the file if it does not exist.
     *
     * @param path The file path of the file to be read from.
     */
    public Storage(String path) {
        this.file = new File(path);

        if (!this.file.exists()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("Sorry! There was an error creating the file! :'(");
                e.printStackTrace();
            }
        }
        this.ui = new Ui();

    }

    /**
     * Reads the tasks from the file and returns an ArrayList of tasks.
     *
     * @return An ArrayList of tasks read from the file.
     * @throws FileException If the file is not found or is corrupted.
     */
    public ArrayList<Task> readTask() throws FileException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        // Read tasks from file
        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");

                String type = parts[0];
                String markOrUnmark = parts[1];
                String description = parts[2];

                if (type.equals("T")) {
                    Task task = new Todo(description);
                    tasks.add(handleMarkOrUnmark(task, markOrUnmark));

                } else if (type.equals("D")) {
                    String by = parts[3];
                    Task task = new Deadline(description, by);
                    tasks.add(handleMarkOrUnmark(task, markOrUnmark));

                } else if (type.equals("E")) {
                    String from = parts[3];
                    String to = parts[4];
                    Task task = new Event(description, from, to);
                    tasks.add(handleMarkOrUnmark(task, markOrUnmark));

                }
            }
        } catch (FileNotFoundException e) {
            throw new FileException("Sorry! File not found! :'(");

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FileException("Sorry! File is corrupted! :'(\nWill not be able to read tasks from file! :'(");

        } catch (DateException e) {
            ui.getErrorMessage(e.getMessage() + "\nPlease enter the date in proper format such as dd/MM/yyyy or "
                    + "yyyy-MM-dd\nYou can also enter the time in 24-hour format such as HH[:mm] after the date");

        }
        return tasks;
    }

    private Task handleMarkOrUnmark(Task task, String markOrUnmark) {
        if (markOrUnmark.equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Writes the list of tasks to the data file.
     *
     * @param tasks The TaskList of tasks to be written to the file.
     * @throws FileException If there is an error editing the file.
     */
    public void writeTask(TaskList tasks) throws FileException {
        try (FileWriter fw = new FileWriter(this.file, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toDataString() + "\n");
            }

        } catch (IOException e) {
            throw new FileException("Sorry! There was an error editing the file! :'(");
        }


    }
}
