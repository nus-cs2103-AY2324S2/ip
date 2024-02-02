import java.util.ArrayList;

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

    public String toString() {
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
