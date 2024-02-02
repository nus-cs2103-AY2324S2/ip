package baron.Dao;

import baron.Database.Database;
import baron.Enums.TaskType;
import baron.Models.Todo;
import baron.Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TodoDao extends TaskDao {
    public static final String NAME = TaskType.TODO.getCommand();

    /**
     * Creates a todo object from the given input string
     *
     * @param input input string with the format todo TODO NAME
     * @return Created Todo object
     */
    public static Todo getFrom(String input) {
        String value = StringUtils.getValueOfCommand(input, TodoDao.NAME, null);
        return new Todo(value);
    }

    /**
     * Gets all todos  from the deadline.txt file
     *
     * @return Returns a list of todos that was parsed
     */
    public static List<Todo> getTodos() {
        File table = Database.getTable(NAME);
        List<Todo> todos = new ArrayList<>();
        try {
            Files.lines(table.toPath()).forEach(line -> {
                Todo todo = Todo.fromDataString(line);
                todos.add(todo);
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
