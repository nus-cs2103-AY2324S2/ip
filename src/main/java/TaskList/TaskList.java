package TaskList;

import Tasks.Task;

import java.util.ArrayList;

import java.util.Iterator;


public class TaskList implements Iterable<Task>{
    private final ArrayList<Task>  tasks;
    public TaskList(ArrayList<Task> list){
        this.tasks = list;
    }
    public TaskList(){
        tasks = new ArrayList<>();
    }
    public int size(){
        return tasks.size();
    }

    public void addToList(Task t) {
        tasks.add(t);
    }

    public String showLists() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
    }
    public String removeTask(int index) throws IllegalArgumentException {
        if (!isValidIndex(index)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(index - 1);
            tasks.remove(index - 1);
            return t.toString();
        }

    }

    public String showMark(int taskNumber) throws IllegalArgumentException {
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            return t.toString();
        }
    }

    public String showUnmark(int taskNumber) throws IllegalArgumentException {
        //handling invalid index of taskNumber
        if (!isValidIndex(taskNumber)) {
            throw new IllegalArgumentException("Please input valid number. to see the available number(s) for your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            return t.toString();
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    public Iterator<Task> iterator() {
        return tasks.iterator();
    }


}
