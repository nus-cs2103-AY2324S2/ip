package Riri;

import java.util.ArrayList;
public class MyList {
    private ArrayList<Task> mylist = new ArrayList<>();
    public void returnList() {
        for (int i = 1; i < mylist.size()+1; i++) {
            System.out.println(i + ". " + mylist.get(i-1).toString());
        }
        return;
    }
    public void addTask(Task item) {
        mylist.add(item);
        System.out.println("Got it. Added: " + item.toString());
    }
    public void loadTask(Task item) {
        mylist.add(item);
    }
    public void mark(int i) {
        this.mylist.get(i-1).markDone();
        this.returnList();
    }
    public void unmark(int i) {
        this.mylist.get(i-1).markUndone();
        this.returnList();
    }
    public void delete(int i) {
        this.mylist.remove(0);
        System.out.println("Deleted task no. " + i);
        System.out.println("You have " + this.len() + " tasks left");
    }
    public int len() {
        return mylist.size();
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i < mylist.size()+1; i++) {
            s += (mylist.get(i-1).toString() + "\n");
        }
        return s;
    }
}