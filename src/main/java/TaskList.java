import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private ArrayList<Task> taskList = new ArrayList<>();


    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<String> tasks) throws DukeException{
        taskList.clear();
        for (String taskStr: tasks) {
            Task task = TaskParser.parse(taskStr);
            taskList.add(task);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    public ArrayList<Task> getTaskList(){
        return taskList;
    }

    public Task getItemFromList(Task t){
        if (taskList.contains(t)) {
            int index = taskList.indexOf(t);
            return taskList.get(index);
        }
        return null;
    }

    public int getCountByType(String taskType){
        int count = 0;
        count = (int) taskList.stream().filter( obj -> ((Task)obj).getTypeOfTask().equals(taskType)).count();
        return count;
    }
    public Task getItemFromListByIndex(int idx){
        if (idx >= 0 && idx < taskList.size()) {
            Task t = taskList.get(idx);
            if (null != t) {
                int index = taskList.indexOf(t);
                return taskList.get(index);
            }
            return null;
        }
        return null;
    }
    public void add(Task t) {
        taskList.add(t);
        pcs.firePropertyChange("ADD", null, Collections.unmodifiableList(taskList));
    }

    public boolean updateTaskInList(Task t) {
        if (taskList.contains(t)) {
            int index = taskList.indexOf(t);
            taskList.set(index, t);
            pcs.firePropertyChange("UPDATE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }
    public boolean updateTaskInList(int index, Task t) {
        if (taskList.contains(t)) {
            taskList.set(index, t);
            pcs.firePropertyChange("UPDATE", null, taskList);
            System.out.println("tasklist:update:task: "+t.getStringRepresentation());
            return true;
        }
        return false;
    }

    public boolean removeItemAtIndex(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task obj = taskList.get(index);
            taskList.remove(obj);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    public boolean remove(Task t) {
        if (taskList.contains(t)) {
            int index = taskList.indexOf(t);
            Task obj = taskList.get(index);
            taskList.remove(t);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }
    public String printOutput() {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            sb.append(count).append(".");
            sb.append(task.printOutput());
            if (count < taskList.size()) {
                sb.append("\n");
            }
            count++;
        }
        sb.lastIndexOf("\n");
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            sb.append(task.getStringRepresentation());
            if (count < taskList.size()) {
                sb.append("\n");
            }
            count++;
        }
        return sb.toString();
    }

}
