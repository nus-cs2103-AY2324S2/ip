package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list){
        this.taskList = list;
    }
    public TaskList(){
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task current) {
        taskList.add(current);
    }
    public void deleteTask(int index){
        taskList.remove(index - 1);
    }
    public Task getTask(int index){
        return taskList.get(index);
    }

    // takes current tasks in taskList turns them into strings,
    // numbers them and puts them in an arraylist
    public ArrayList<String> showList(){
        ArrayList<String> result = new ArrayList<>();
        int count = 1;
        for (Task i : taskList) {
            result.add(Integer.toString(count) + "." + i.getStatus());
            count++;
        }
        return result;
    }

    public ArrayList<Task> giveList(){
        return this.taskList;
    }

    public int size(){
        return taskList.size();
    }

    public ArrayList<String> find(String key) {
        ArrayList<String> result = new ArrayList<>();
        int count = 1;
        for (Task i : taskList) {
            if (i.getName().contains(key)) {
                result.add(Integer.toString(count) + "." + i.getStatus());
                count++;
            }
        }
        return result;
    }

}
