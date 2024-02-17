package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.dataprocessing.Decoder;
import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Storage class handles the loading and updating of tasks from/to a file.
 * It reads tasks from a specified file and writes tasks back to the same file.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) throws DukeException {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return A list of tasks loaded from the file.
     * @throws DukeException If an error occurs while loading tasks from the file.
     */
    public List<Task> load() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        File f = new File(filepath);
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! File is not found.");
        }
        while (s.hasNext()) {
            Task decodedTask = Decoder.decodeTask(s.nextLine());
            taskList.add(decodedTask);
        }
        return taskList;
    }

    /**
     * Updates the file with the tasks from the given task list.
     *
     * @param taskList The list of tasks to be written to the file.
     * @throws DukeException If an error occurs while updating the file.
     */
    public void update(TaskList taskList) throws DukeException {
        assert taskList != null : "A task list should exist";
        try {
            FileWriter fw = new FileWriter(filepath);
            writeToStorage(taskList, fw);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! An error occurred with the save file. Try again");
        }
    }

    private void writeToStorage(TaskList taskList, FileWriter fw) throws DukeException, IOException {
        for (int i = 0; i < taskList.size(); i++) {
            String parsedTask = taskList.get(i).toFileString();
            fw.write(parsedTask + "\n");
        }
    }
}
