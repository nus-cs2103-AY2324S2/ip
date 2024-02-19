package baron.dao;

import java.io.File;

import baron.database.Database;
import baron.models.Task;
/**
 * This class offers more general functions that encompasses the different Task inheritors like marking as done
 */
public class TaskDao {

    /**
     * Finds a line with the given id and the task file name to mark as done/undone
     *
     * @param id   the id of the task
     * @param name the file name of the object to update. e.g. if marking a todo, then it should be value of
     *             TodoDao NAME
     * @param task The task to modify and update
     * @param done Whether it should be marked as done or not
     * @return Task wit hthe updated values
     */
    public static Task mark(long id, String name, Task task, boolean done) {
        File table = Database.getTable(name);
        task.setDone(done);
        String data = task.toDataString();
        Database.updateById(table.toPath(), id, data);
        return task;
    }

    /**
     * Creates a new task object with a specified file name. Shared with other files like
     * Event, Todo and Deadline
     *
     * @param name Name of the file to create
     * @param task Task object to add
     */
    public static void add(String name, Task task) {
        File table = Database.getTable(name);
        String data = task.toDataString();
        long id = Database.create(table.toPath(), data);
        task.setId((int) id);
    }

    /**
     * Deletes a line wit hthe specified line from the a specific file
     *
     * @param name Name of the file
     * @param id   id of the line to delete
     */
    public static void delete(String name, long id) {
        File table = Database.getTable(name);
        Database.delete(table.toPath(), id);
    }
}
