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

    public void markTask(int index) {
        Task modTask = listOfTasks.get(index - 1);
        modTask.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + modTask);
    }

    public void unmarkTask(int index) {
        Task modTask = listOfTasks.get(index - 1);
        modTask.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + modTask);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }
}
