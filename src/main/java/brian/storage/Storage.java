package brian.storage;

import java.io.IOException;
import java.util.List;

import brian.task.Task;
import brian.task.TaskList;
import brian.utils.DukeException;

/**
 * The interface for storage. It is used to save and load the task list.
 */
public interface Storage {
    void save(TaskList taskList) throws DukeException;

    List<Task> load() throws DukeException, IOException;
}
