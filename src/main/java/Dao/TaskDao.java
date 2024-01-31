package Dao;

import Database.Database;
import Models.Task;
import Models.Todo;

import java.io.File;

/**
 * This class offers more general functions that encompasses the different Task inheritors like marking as done
 */
public class TaskDao {

    /**
     *
     * @param id the id of the task. 1-indexed
     * @param NAME the file name of the object to update. e.g. if marking a todo, then it should be value of
     *             TodoDao NAME
     * @param task The task to modify and update
     * @param done Whether it should be marked as done or not
     * @return
     */
    public static Task mark(int id, String NAME, Task task, boolean done) {
        File table = Database.getTable(NAME);
        task.setDone(done);
        String data = task.toDataString();
        Database.updateById(table.toPath(), id, data);
        return task;
    }
}
