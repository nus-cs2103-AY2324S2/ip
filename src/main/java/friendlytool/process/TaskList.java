package friendlytool.process;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import friendlytool.command.CommandTypes;
import friendlytool.command.Parser;
import friendlytool.task.Deadline;
import friendlytool.task.Event;
import friendlytool.task.Task;
import friendlytool.task.ToDo;

/**
 * List that can store different types of task, and provide important features to modify them.
 */
public class TaskList {

    private List<Task> myList;

    /**
     * Constructs the task list.
     */
    public TaskList() {
        this.myList = new ArrayList<>();
    }

    /**
     * Adds the task to the list
     *
     * @param s  string containing information of the task.
     * @param ct command type.
     * @return a string that the task is added.
     * @throws FtException if no description is provided.
     */
    public String addTask(String s, CommandTypes ct) throws FtException {
        Task task;
        switch (ct) {
        case TODO:
            String todo = Parser.parseToDo(s);
            if (!todo.isEmpty()) {
                task = new ToDo(todo, false);
            } else {
                throw new FtException("Error: Please tell me what you have TO DO");
            }
            break;

        case DEADLINE:
            String[] parsedDl = Parser.parseDeadline(s);
            String dt = parsedDl[0];
            String by = parsedDl[1];
            if (!dt.isEmpty() && !by.isEmpty()) {
                try {
                    task = new Deadline(dt, false, new Date(by));
                } catch (DateTimeParseException e) {
                    throw new FtException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
                }
            } else {
                throw new FtException("Error: Please tell me your task and its deadline");
            }
            break;

        case EVENT:
            String[] parsedEvent = Parser.parseEvent(s);
            String name = parsedEvent[0];
            String from = parsedEvent[1];
            String to = parsedEvent[2];
            if (!name.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                try {
                    task = new Event(name, false, new Date(from), new Date(to));
                } catch (DateTimeParseException e) {
                    throw new FtException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
                }
            } else {
                throw new FtException("Error: Please tell me your event and its from/to dates");
            }
            break;
        default:
            throw new FtException("Error: Invalid Task Type");
        }
        myList.add(task);
        return UI.getUpdateTaskMsg(task, myList.size());
    }


    /**
     * Mark the task as done, based on the given index.
     *
     * @param s index for the task, in the string format.
     * @return string that the task is marked.
     * @throws FtException if invalid index is provided.
     */
    public String mark(String s) throws FtException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.mark();
            return UI.getMarkMsg(task);
        } else {
            throw new FtException("Error: Please provide valid index");
        }
    }

    /**
     * Unmarks the task based on the given index.
     *
     * @param s index for the task, in the string format.
     * @return string that the task is unmarked.
     * @throws FtException if invalid index is provide.
     */
    public String unmark(String s) throws FtException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.unmark();
            return UI.getUnmarkMsg(task);
        } else {
            throw new FtException("Error: Please provide valid index");
        }
    }

    /**
     * Deletes the task based on the given index.
     *
     * @param s index for the task, in the string format.
     * @return string that the task is deleted.
     * @throws FtException if invalid index is provided.
     */
    public String deleteTask(String s) throws FtException {
        int i = Parser.parseNumber(s);
        if ((0 < i) && (i <= myList.size())) {
            String task = myList.remove(i - 1).toString();
            return UI.getDeleteMsg(task, myList.size());
        } else {
            throw new FtException("Error: Please provide valid index");
        }
    }

    /**
     * Provides the number of elements in the list.
     *
     * @return number of elements.
     */
    public int size() {
        return myList.size();
    }

    /**
     * Provides the task based on the given index.
     *
     * @param i index
     * @return task based on the index.
     */
    public Task get(int i) {
        return myList.get(i);
    }

    /**
     * Adds task to the list
     *
     * @param task task given.
     */
    public void add(Task task) {
        myList.add(task);
    }
}
