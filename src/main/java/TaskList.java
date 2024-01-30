import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void delete(Task t) {
        this.taskList.remove(t);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int id) {
        return this.taskList.get(id);
    }

    public String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i<this.getSize(); i++) {
            Task task = this.getTask(i);
            String message = Integer.toString(i+1) + "." +"[" +task.getType()+"]"+"[" + task.getStatusIcon() +"] " + task.getDescription() + task.getExtraInfo()+"\n";
            list += message;

        }
        return list;
    }


}
