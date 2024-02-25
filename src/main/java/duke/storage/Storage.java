package duke.storage;

import java.io.IOException;
import java.util.List;

import duke.task.Task;
import duke.task.TaskList;
import duke.utils.DukeException;

/**
 * The interface for storage. It is used to save and load the task list.
 */
public interface Storage {
    void save(TaskList taskList) throws DukeException;

    List<Task> load() throws DukeException, IOException;
}
