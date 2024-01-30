package Yapchit;

import Yapchit.Tasks.Task;
import Yapchit.YapchitExceptions.InvalidDetailException;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public void delete(int idx){
        this.list.remove(idx);
    }

    public void mark(int idx, boolean val) throws InvalidDetailException{
        if(idx >= list.size()){
            throw new InvalidDetailException("Invalid item index, please try again");
        } else {
            list.get(idx).updateTag(val);
        }
    }

    public void addTask(Task t){
        list.add(t);
    }

    public int getListSize(){
        return this.list.size();
    }

    public Task getItem(int i){
        return this.list.get(i);
    }
}
