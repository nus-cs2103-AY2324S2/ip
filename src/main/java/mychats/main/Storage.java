package duke.main;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Loads tasks from the file and saves tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage instance with the given file path.
     *
     * @param filePath File path for storing and loading tasks.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file given by the file path.
     *
     * @return ArrayList of Task objects loaded from the file.
     * @throws DukeException If an error occurs during the loading
     * process or the file has an incorrect format.
     */
    ArrayList<Task> loadList() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String input;
            while ((input = br.readLine()) != null) {
                String[] splitInput = input.split(" \\| ");
                Task task;
                if (splitInput[0].equals("T")) {
                    task = new Todo(splitInput[2]);
                } else if (splitInput[0].equals("D")) {
                    task = new Deadline(splitInput[2], splitInput[3]);
                } else if (splitInput[0].equals("E")) {
                    task = new Event(splitInput[2], splitInput[3], splitInput[4]);
                } else {
                    throw new DukeException("Error! Incorrect duke.txt format: unexpected task type.");
                }
                if (Integer.parseInt(splitInput[1]) == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new DukeException("Error! An IOException occurred.");
        } catch (NumberFormatException e) {
            throw new DukeException("Error! Incorrect duke.txt format: unexpected value. Value should be 1 for done or 0 for not done.");
        }
        return tasks;
    }
    
    /**
     * Saves the given list of tasks to the file in the given filePath.
     *
     * @param list ArrayList of Task objects to be saved.
     * @throws DukeException If an IOException occurs.
     */
    public void saveList(ArrayList<Task> list) throws DukeException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task l : list) {
                bw.append(l.toFileString()).append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred.");
        }
    }
}