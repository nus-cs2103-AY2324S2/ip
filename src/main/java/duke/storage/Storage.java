package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Manages the loading and saving of tasks to a file.
 * This class provides functionality to loadFromFile tasks from a specified file
 * upon startup and save tasks into the same file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object to handle data persistence.
     * The file path for loading and saving tasks is set upon construction.
     */
    public Storage() {
        this.filePath = "./data/tasks.txt";
    }

    /**
     * Loads tasks from the file specified by the filePath.
     * Parses the task data from the file and creates a corresponding list of Task objects.
     *
     * @return An ArrayList of Task objects loaded from the file.
     * @throws DukeException if the file cannot be found or the data format is incorrect.
     */
    public ArrayList<Task> loadFromFile() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            File directory = new File("./data/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(filePath);
            Scanner whatToDo = new Scanner(file);

            while (whatToDo.hasNext()) {
                String readText = whatToDo.nextLine();
                char taskType = readText.charAt(1);
                char doneOrNot = readText.charAt(5);
                String description = readText.substring(9);

                Task task = null;

                if (taskType == 'T') {
                    task = new ToDo(description);
                    listOfTasks.add(task);
                } else if (taskType == 'D') {
                    try {
                        task = new Deadline(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        task = new Event(description);
                        listOfTasks.add(task);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (doneOrNot == '1') {
                    task.markComplete();
                }

            }
        } catch (FileNotFoundException e) {
            throw new DukeException(" No Saved Tasks Found. Let's start with an empty list!\n");
        }

        return listOfTasks;
    }


    /**
     * Saves the current list of tasks to the file specified by the filePath.
     * Converts the list of Task objects into a string representation that can be saved to a file.
     *
     * @param listOfTasks The list of Task objects to be saved.
     * @throws DukeException if there is an error saving the data to the file.
     */
    public void saveToFile(ArrayList<Task> listOfTasks) throws DukeException {
        String toSave = "";

        for (int i = 0; i < listOfTasks.size(); i++) {
            toSave += listOfTasks.get(i).toSave() + "\n";
        }

        try {
            FileWriter file = new FileWriter(this.filePath);
            file.write(toSave);
            file.close();
        } catch (IOException e) {
            throw new DukeException(" Error while saving, please try again!\n");
        }
    }
}
