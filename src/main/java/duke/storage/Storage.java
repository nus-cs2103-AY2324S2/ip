package duke.storage;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    public Storage(String filepath) {

    }

    public List<Task> load() throws DukeException {
        return new ArrayList<>();
    }
}
