import java.util.ArrayList;

public class Tracker {

    private ArrayList<Task> taskList;

    public Tracker(){
        taskList = new ArrayList<>();
    }

    public void addTask(Task element) {
        taskList.add(element);
        System.out.println("added: " + element);
    }

    public void listTasks() {
        int index = 0;
        for (Task element: taskList) {
            index++;
            System.out.println(index + "." + element.listTaskString());
        }
        if (taskList.size() == 0) {
            System.out.println("No items in list!");
        }
    }



}
