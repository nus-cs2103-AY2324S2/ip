package fireraya.main;

import fireraya.exception.FirerayaException;
import fireraya.task.Deadline;
import fireraya.task.Event;
import fireraya.task.Task;
import fireraya.task.Todo;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class deals with the storing and loading of the tasks in the program.
 *
 * As this program supports auto-saving and loading of tasks,
 * this class deals with saving and loading the data into memory.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param filePath path of the data file in memory.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Saves the current tasks in the program into a file specified in the filepath.
     *
     * @param tasks ArrayList of tasks held by the program.
     */
    public void saveToFile(ArrayList<Task> tasks) throws FirerayaException {
        try {
            File file = new File(filePath);

            BufferedWriter saveText = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks) {
                saveText.append(t.saveFormat());
                saveText.append("\n");
            }
            saveText.close();
        } catch (IOException e) {
            throw new FirerayaException("An IOException occurred. " + e);
        }
    }

    /**
     * Loads the current tasks in the program from the file specified in the filepath.
     *
     * @return an ArrayList of tasks in readable format for the program.
     */
    public ArrayList<Task> load() throws FirerayaException {
        ArrayList<Task> loaded = new ArrayList<>();

        try {
            File curr = new File(filePath);
            if (!curr.exists()) {
                return loaded;
            }

            Scanner s = new Scanner(curr);
            while (s.hasNext()) {
                String input = s.nextLine();
                String[] all = input.split("\\|");
                String keyword = all[0];

                Task nextTask = null;

                if (keyword.equals("T")) {
                    nextTask = new Todo(all[2]);
                }

                if (keyword.equals("D")) {
                    nextTask = new Deadline(all[2], all[3]);
                }

                if (keyword.equals("E")) {
                    nextTask = new Event(all[2], all[3], all[4]);
                }

                if (all[1].equals("1")) {
                    assert nextTask != null;
                    nextTask.markAsDone();
                }

                loaded.add(nextTask);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return loaded;
    }
}



