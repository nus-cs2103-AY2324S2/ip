package duke.storage;

import duke.exceptions.DukeException;
import duke.dataprocessing.Decoder;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filepath;

    public Storage(String filepath) throws DukeException {
        this.filepath = filepath;
    }

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
            Task decoded = Decoder.decodeTask(s.nextLine());
            taskList.add(decoded);
        }
        return taskList;
    }

    public void update(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (int i = 0; i < taskList.size(); i++) {
                String parsedTask = taskList.get(i).toFileString();
                fw.write(parsedTask + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! An error occurred with the save file. Try again");
        }
    }
}
