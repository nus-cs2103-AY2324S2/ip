package duke.main;
import duke.exception.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Storage {
    private TaskList taskList;
    private String path;

    public Storage(TaskList taskList, String path) throws IOException, FileNotFoundException {
        this.taskList = taskList;
        this.path = path;
        if (!(new File(path).exists())) {
            throw new FileNotFoundException();
        }
    }

    public void store() throws IOException {
        FileWriter writer = new FileWriter(path);
        for (int i = 0; i < taskList.size(); i++) {
            writer.write(i == 0 ? (i + 1) + ": " + taskList.get(i).toString()
                    : System.lineSeparator() + (i + 1) + ": " + taskList.get(i).toString());
        }
        writer.close();
    }

}
