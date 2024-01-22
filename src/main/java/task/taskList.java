package task;

import java.util.List;
import java.util.ArrayList;

public class taskList {
    private final List<Task> listOfTasks;

    public taskList() {
        listOfTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
        System.out.println("added: " + task);
    }

    public void listDownTask() {
        int size = listOfTasks.size();
        for(int i = 1; i <= size; i++) {
            System.out.println(i +": " + listOfTasks.get(i - 1));
        }
    }
}
