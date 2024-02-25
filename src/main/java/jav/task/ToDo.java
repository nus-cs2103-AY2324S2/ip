package jav.task;

import jav.exception.InvalidParamException;
import jav.manager.StorageManager;

/**
* A "ToDo" is a task. (For the time being, it is basically the same)
*/
public class ToDo extends Task {
    /**
     * Constructs a new ToDo.
     *
     * @return a new ToDo.
     */
    public ToDo() {
        type = StorageManager.StorageType.TODO;
        description = "todo";
        isMarked = false;
    }

    /**
     * Constructs a new todo.
     *
     * @param params a string containing the information about the todo.
     * @param isMarked whether the todo is marked.
     * @return a new todo.
     * @throws InvalidParamException if the parameters are invalid.
     */
    public ToDo(String params, boolean isMarked) throws InvalidParamException {
        type = StorageManager.StorageType.TODO;
        description = params;
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getFileFormatParam() {
        return description;
    }
}
