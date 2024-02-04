package panda.component;
import java.util.ArrayList;

import panda.command.Command;
import panda.exception.PandaException;
import panda.task.Task;

public class TaskList {
    private ArrayList<Task> tlist;

    public TaskList() {
        tlist = new ArrayList<>();
    }

    public TaskList(ArrayList<Command> clist) throws PandaException {
        tlist = new ArrayList<>();
        for(Command c : clist) {
            c.execute(this);
        }
    }

    public void insert(Task task) {
        tlist.add(task);
    }

    public void remove(int idx) {
        tlist.remove(tlist.get(idx - 1));
    }

    public void mark(int idx) {
        tlist.get(idx - 1).mark();
    }

    public void unmark(int idx) {
        tlist.get(idx - 1).unmark();
    }

    public int size() {
        return tlist.size();
    }

    public String taskString(int idx) {
        return tlist.get(idx).toString();
    }

    /**
     * Searches for tasks that match the given filter string.
     * The filter string is treated as a regular expression and the comparison is case insensitive.
     * 
     * @param fString the filter string to match against.
     * @return a new TaskList containing only the tasks that match the filter string.
     */
    public TaskList find(String fString) {
        TaskList tmp = new TaskList();
        for(Task task : tlist) {
            if(task.match(fString)) {
                tmp.insert(task);
            }
        }
        return tmp;
    }

    public String toString() {
        if(tlist.size() == 0) {
            return "Your list is empty.";
        }

        String result = "Here are the tasks in your list:";
        for(int i = 0; i < tlist.size(); i++) {
            result = result + "\n" + ((i + 1) + "." + tlist.get(i));
        }
        return result;
    }

    public String saveString() {
        String result = "";
        for(Task t : tlist) {
            result = result + t.saveString() + "\n";
        }
        return result;
    }
}
