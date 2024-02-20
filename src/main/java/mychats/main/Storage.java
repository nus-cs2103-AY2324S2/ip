package mychats.main;
import mychats.exception.MyChatsException;
import mychats.task.Deadline;
import mychats.task.Event;
import mychats.task.Todo;
import mychats.task.Task;

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
     * @throws MyChatsException If an error occurs during the loading
     * process or the file has an incorrect format.
     */
    ArrayList<Task> loadList() throws MyChatsException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            loadTasksFromReader(br, tasks);
        } catch (IOException e) {
            throw new MyChatsException("Error! An IOException occurred.");
        }
        return tasks;
    }

    private void loadTasksFromReader(BufferedReader br, ArrayList<Task> tasks)
            throws MyChatsException, IOException {
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
                throw new MyChatsException("Error! Incorrect mychats.txt format: unexpected task type.");
            }
            if (Integer.parseInt(splitInput[1]) == 1) {
                task.markTask();
            }
            tasks.add(task);
        }
    }
    
    /**
     * Saves the given list of tasks to the file in the given filePath.
     *
     * @param list ArrayList of Task objects to be saved.
     * @throws MyChatsException If an IOException occurs.
     */
    public void saveList(ArrayList<Task> list) throws MyChatsException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task l : list) {
                bw.append(l.toFileString()).append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new MyChatsException("An IOException occurred.");
        }
    }
}