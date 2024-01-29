package grumblebug;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }
    public void printList() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(i+1 + this.list.get(i).getFullStatus());
        }
    }
    public ArrayList<Task> getList() {
        return this.list;
    }
    public void add(Task task) {
        this.list.add(task);
    }
    public int size() {
        return this.list.size();
    }

    public Task get(int i) {
        try {
            return this.list.get(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
        return null;
    }

    public void delete(int i) {
        try {
            this.list.remove(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
    }
}
