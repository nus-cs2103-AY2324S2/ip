package friendlytool.process;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
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
        boolean isMissingElement = false;
        switch (ct) {
        case TODO:
            String todo = Parser.parseToDo(s);
            isMissingElement = todo.isEmpty();

            if (isMissingElement) {
                throw new FtException("Error: Please tell me what you have TO DO");
            }

            task = new ToDo(todo, false);
            break;

        case DEADLINE:
            String[] parsedDl = Parser.parseDeadline(s);
            String dt = parsedDl[0];
            String by = parsedDl[1];
            isMissingElement = dt.isEmpty() || by.isEmpty();

            if (isMissingElement) {
                throw new FtException("Error: Please tell me your task and its deadline");
            }

            try {
                task = new Deadline(dt, false, new Date(by));
            } catch (DateTimeParseException e) {
                throw new FtException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
            }
            break;

        case EVENT:
            String[] parsedEvent = Parser.parseEvent(s);
            String name = parsedEvent[0];
            String from = parsedEvent[1];
            String to = parsedEvent[2];
            isMissingElement = name.isEmpty() || from.isEmpty() || to.isEmpty();

            if (isMissingElement) {
                throw new FtException("Error: Please tell me your event and its from/to dates");
            }

            try {
                task = new Event(name, false, new Date(from), new Date(to));
            } catch (DateTimeParseException e) {
                throw new FtException("Invalid date format. Please follow yyyy-mm-ddThh:mm format.");
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
        boolean isInvalidIndex = (i <= 0) || (i > myList.size());

        if (isInvalidIndex) {
            throw new FtException("Error: Please provide valid index");
        }

        Task task = myList.get(i - 1);
        task.mark();
        return UI.getMarkMsg(task);
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
        boolean isInvalidIndex = (i <= 0) || (i > myList.size());

        if (isInvalidIndex) {
            throw new FtException("Error: Please provide valid index");
        }

        Task task = myList.get(i - 1);
        task.unmark();
        return UI.getUnmarkMsg(task);
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
        boolean isInvalidIndex = (i <= 0) || (i > myList.size());

        if (isInvalidIndex) {
            throw new FtException("Error: Please provide valid index");
        }

        String task = myList.remove(i - 1).toString();
        return UI.getDeleteMsg(task, myList.size());
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

    /**
     * Sorts the list so that todo comes first and sort rest by chronological order.
     *
     * @return string that task is sorted
     */
    public String sortTask() {
        myList.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                if (t1 instanceof ToDo && t2 instanceof ToDo) {
                    return 0;
                } else if (t1 instanceof ToDo) {
                    return -1;
                } else if (t2 instanceof ToDo) {
                    return 1;
                } else {
                    Date d1 = t1.getEndTime();
                    Date d2 = t2.getEndTime();
                    return d1.compareTo(d2);
                }
            }
        });
        return UI.getSortTaskMsg();
    }
}
