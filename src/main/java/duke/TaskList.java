package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> myList;

    public TaskList(ArrayList<Task> myList) {
        this.myList = myList;
    }

    public TaskList() {
        this.myList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.myList.add(task);
    }

    public Task getTask(int i) {
        return this.myList.get(i - 1);
    }

    public void deleteTask(int index) {
        myList.remove(index - 1);
    }

    public void showTasks() {
        int index = 1;
        for (Task s : this.myList) {
            System.out.println(index + "." + s);
            index++;
        }
    }

    public int getSize() {
        return this.myList.size();
    }

    public ArrayList<Task> getListOfTasks() {
        return this.myList;
    }

    public void markTask(int index) {
        this.myList.get(index).setDone();
    }

    public void unMarkTask(int index) {
        this.myList.get(index).setNotDone();
    }




}
