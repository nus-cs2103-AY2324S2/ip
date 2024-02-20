package signal.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void addTask(Task current) {
        assert current != null : "task added cannot be null";
        taskList.add(current);
    }

    public void deleteTask(int index){
        taskList.remove(index);
    }

    public ArrayList<String> showList(){
        return (ArrayList<String>) IntStream.range(0, taskList.size())
                .mapToObj(i -> (i+1) + "." + taskList.get(i).toString())
                .collect(Collectors.toList());
    }

    public ArrayList<Task> giveList() {
        return this.taskList;
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<String> find(String key) {
        ArrayList<String> result = new ArrayList<>();
        int count = 1;
        for (Task i : taskList) {
            if (i.getDescription().contains(key)) {
                result.add(Integer.toString(count) + "." + i.toString());
                count++;
            }
        }
        return result;
    }

}

