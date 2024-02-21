package arona.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import arona.exception.AronaInvalidDateException;

import arona.task.Deadline;
import arona.task.Event;
import arona.task.Task;
import arona.task.TaskList;
import arona.task.ToDo;

import arona.ui.Ui;

/**
 * The Storage class can modify and store the task list.
 * The task list is stored locally in a .txt file.
 *
 * @author Maximilliano Utomo
 */
public class Storage {
    /**
     * A public constructor for the Storage class.
     */
    public Storage() {
        try {
            File dataFolder = new File("./data");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File dataFile = new File("./data/data.txt");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            assert dataFile.exists() : "File should exist";
        } catch (IOException error) {
            Ui.printLines("Sorry, Sensei! I seem to be struggling to load the tasks :(");
        }
    }

    /**
     * Fetches the locally saved data containing the task list.
     * @throws IOException - an exception thrown if the data is not read
     */
    public void loadTaskListFromStorage(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File dataFile = new File("./data/data.txt");
        Scanner istream = new Scanner(dataFile);

        while (istream.hasNextLine()) {
            String[] streamSplit = istream.nextLine().split(Pattern.quote("|"), 2);
            String taskType = streamSplit[0];
            String[] taskArguments = streamSplit[1].split(Pattern.quote("|"), 0);

            Task task;
            try {
                if (taskType.equals("T")) {
                    task = new ToDo(taskArguments[1]);
                } else if (taskType.equals("D")) {
                    task = new Deadline(taskArguments[1], taskArguments[2]);
                } else { // if (taskType.equals("E"))
                    task = new Event(taskArguments[1], taskArguments[2], taskArguments[3]);
                }
            } catch (AronaInvalidDateException e) {
                Ui.printLines("Sorry, Sensei! I seem to be struggling to load the tasks :(");
                break;
            }

            if (taskArguments[0].equals("1")) {
                task.setDone();
            } else {
                task.setNotDone();
            }

            tasks.add(task);
        }

        istream.close();
        taskList.readTasksFromStorage(tasks);
    }

    /**
     * Saves the task list to a local save data.
     */
    public void saveTaskListToStorage(TaskList taskList) {
        try {
            FileWriter ostream = new FileWriter("./data/data.txt");
            for (int i = 0; i < taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                ostream.write(task.toDataFormat() + "\n");
            }
            ostream.close();
        } catch (IOException error) {
            Ui.printLines("Sorry, Sensei! I seem to be struggling to save the tasks :(");
        }
    }
}
