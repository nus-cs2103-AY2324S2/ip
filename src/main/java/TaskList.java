import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;

public class TaskList<T> {
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private ArrayList<T> taskList;


    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener){
        pcs.removePropertyChangeListener(listener);
    }

    public ArrayList<T> getTaskList(){
        return taskList;
    }

    public T getItemFromList(T t){
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
    public T getItemFromListByIndex(int idx){
        if (idx >= 0 && idx < taskList.size()) {
            T t = taskList.get(idx);
            if (null != t) {
                int index = taskList.indexOf(t);
                return taskList.get(index);
            }
            return null;
        }
        return null;
    }
    public void add(T t) {
        taskList.add(t);
        pcs.firePropertyChange("ADD", null, Collections.unmodifiableList(taskList));
    }

    public boolean updateTaskInList(T t) {
        if (taskList.contains(t)) {
            int index = taskList.indexOf(t);
            taskList.set(index, t);
            pcs.firePropertyChange("UPDATE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }
    public boolean updateTaskInList(int index, T t) {
        if (taskList.contains(t)) {
            taskList.set(index, t);
            pcs.firePropertyChange("UPDATE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    public boolean removeItemAtIndex(int index) {
        if (index >= 0 && index < taskList.size()) {
            T obj = taskList.get(index);
            taskList.remove(obj);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }

    public boolean remove(T t) {
        if (taskList.contains(t)) {
            int index = taskList.indexOf(t);
            T obj = taskList.get(index);
            taskList.remove(t);
            pcs.firePropertyChange("REMOVE", null, Collections.unmodifiableList(taskList));
            return true;
        }
        return false;
    }
    public String printOutput() {
        StringBuilder sb = new StringBuilder();
        for (T task : taskList) {
            sb.append(((Task)task).printOutput()).append("\n");
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (T task : taskList) {
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

}
