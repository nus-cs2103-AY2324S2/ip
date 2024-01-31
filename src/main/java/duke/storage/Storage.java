package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.utils.DukeException;

import java.io.IOException;
import java.util.List;

public interface Storage {
    void save(TaskList taskList) throws DukeException;

    List<Task> load() throws DukeException, IOException;
}
