package datuk;
import java.util.ArrayList;
import java.util.Comparator;

import datuk.task.Task;
import datuk.task.Todo;
import datuk.task.Deadline;
import datuk.task.Event;

/**
 *  Keeps a list of tasks and handles addition or deletion of items in the list.
 */


public class TaskList {

    private ArrayList<Task> list;
    private UI ui;
    private Parser parser;
    public TaskList(ArrayList<Task> list) {
        ui = new UI();
        parser = new Parser();
        this.list = list;
    }

    public TaskList() {
        ui = new UI();
        this.list = new ArrayList<>();
    }

    /**
     * Adds task of type todo, deadline or event to the TaskList.
     *
     * @param cmd array of strings containing the task type and the parameters.
     * @throws DukeException when deadline parameter is not a valid date.
     */
    public String addItem(String[] cmd) throws DukeException {
        Task t;

        if (cmd[0].equals("todo")) {
            t = new Todo(cmd[1]);
            list.add(t);
        } else if (cmd[0].equals("deadline")) {
            t = new Deadline(cmd[1], parser.validDate(cmd[2]));
            list.add(t);
        } else {
            t = new Event(cmd[1], cmd[2], cmd[3]);
            list.add(t);
        }

        return ui.showAddMsg(t, list.size());
    }

    /**
     * Removes an item from the TaskList if it exists.
     *
     * @param str array of strings containing the delete command and the index to be deleted.
     * @throws DukeException when index does is out of bounds.
     */
    public String deleteItem(String[] str) throws DukeException {
        int index = Integer.parseInt(str[1]) - 1;

        try {
            list.get(index);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("Index does not exist!");
        }

        String out = ui.showDeleteMsg(list.get(index), list.size());
        list.remove(index);
        return out;
    }

    /**
     * Marks/unmarks tasks as complete/incomplete
     *
     * @param cmd an array of strings containing mark/unmark command and the index to be marked.
     * @throws DukeException when index is out of bounds.
     */
    public String marked(String[] cmd) throws DukeException {
        int index = Integer.parseInt(cmd[1]);

        index--;

        try {
            list.get(index);
        } catch (IndexOutOfBoundsException  e) {
            throw new DukeException("Index does not exist!");
        }

        if (cmd[0].equals("mark")) {
            String out = ui.showMark(list.get(index).getDesc());
            list.get(index).setCheck(true);
            return out;
        } else {
            String out = ui.showUnmark(list.get(index).getDesc());
            list.get(index).setCheck(false);
            return out;
        }
    }

    /**
     * Matches the keyword to all descriptions in the list and prints a list of tasks that match.
     *
     * @param cmd an array of strings containing find and the keyword.
     */
    public String findItem(String[] cmd) {
        String search = cmd[1];
        ArrayList<Task> task = new ArrayList<>();
        for (Task t : list) {
            if (t.getDesc().contains(search)) {
                task.add(t);
            }
        }

        return ui.printFindList(task);
    }

    /**
     * Returns a Deadline task with the earliest expiring date.
     * If there are no deadline tasks, the method will return none.
     *
     */
    public String reminder() {
        ArrayList<Deadline> task = new ArrayList<>();
        for (Task t : list) {
            if (t.getType().equals("D") && t.getCheck().equals(" ")) {
                task.add((Deadline) t);
            }
        }
        if (task.isEmpty()) {
            return ui.showReminder("none");
        }

        task.sort(new Comparator<Deadline>() {
            @Override
            public int compare(Deadline o1, Deadline o2) {
                return o1.getLocalDate().compareTo(o2.getLocalDate());
            }
        });

        return ui.showReminder(task.get(0).getDesc(), task.get(0).getDate());
    }

    public ArrayList<Task> get() {
        return list;
    }
}
