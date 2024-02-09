package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.exceptions.TaskCreationException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * This class implements the saving and reading of bot data into a text file.
 *
 * @author delishad21
 */
public class Storage {
    private File f;

    /**
     * Creates a Storage object, takes in a filepath for the file in which data
     * should be saved.
     *
     * @param filePath File to save data to and load data from.
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.f = new File(filePath);
        checkAndCreateFile();
    }

    /**
     * Checks and creates file based on filepath.
     *
     * @throws IOException
     */
    private void checkAndCreateFile() throws IOException {
        // Reading and creating data save file
        // making data folder
        if (!f.getParentFile().exists()) {
            if (!f.getParentFile().mkdir()) {
                throw new IOException("Unable to make directory");
            }
        }

        // making data file
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Reads save data from save file.
     *
     * @param ui Used for printing information.
     * @return TaskList read from save data.
     * @throws FileNotFoundException
     */
    public TaskList readSaveData() throws FileNotFoundException {
        TaskList taskList = new TaskList();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            try {
                taskList.add(parseTaskFromSave(s.nextLine()));
            } catch (TaskCreationException e) {
                System.out.println("Error in reading task: " + e.getMessage());
            }
        }
        s.close();

        return taskList;

    }

    /**
     * Saves data from bot back to save file.
     *
     * @param data The data from the bot.
     * @param ui Used for printing information.
     * @throws IOException
     */
    public String saveTodoData(TaskList data) throws IOException {
        this.checkAndCreateFile();
        assert this.f.exists();

        FileWriter fw = new FileWriter(f);

        String dataString = "";

        for (int i = 1; i <= data.size(); i++) {
            dataString = dataString + data.get(i).toSave() + "\n";
        }

        fw.write(dataString);
        fw.close();

        return data.size() + " tasks saved";
    }

    /**
     * Parses individual line from save file into Tasks.
     *
     * @param task Each line read from save file.
     * @return A Task object generated with information parsed from input.
     * @throws TaskCreationException
     */

    private Task parseTaskFromSave(String task) throws TaskCreationException {
        String[] taskSplit = task.split("\\|");
        boolean isDone;
        if (taskSplit[1].equals("[X]")) {
            isDone = true;
        } else if (taskSplit[1].equals("[ ]")) {
            isDone = false;
        } else {
            throw new TaskCreationException("Unable to determine if task (" + task + ") is done or not");
        }
        switch (taskSplit[0]) {
        case "[T]":
            return new Todo(isDone, taskSplit[2]);
        case "[D]":
            return new Deadline(isDone, taskSplit[2],
                                LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER));
        case "[E]":
            return new Event(isDone, taskSplit[2],
                             LocalDateTime.parse(taskSplit[3], Parser.INPUT_DT_FORMATTER),
                             LocalDateTime.parse(taskSplit[4], Parser.INPUT_DT_FORMATTER));
        default:
            throw new TaskCreationException("No such task: " + taskSplit[0] + " for " + task);
        }
    }

}
