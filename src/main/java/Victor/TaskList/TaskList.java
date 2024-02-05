package Victor.TaskList;

import Victor.TaskType.Task;
import Victor.Ui.Ui;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> currentTaskList;
    Ui ui = new Ui();
    public TaskList(ArrayList<Task> currentTaskList) {
        this.currentTaskList = currentTaskList;
    }

    public TaskList() {
        this.currentTaskList = new ArrayList<Task>();
    }

    public void printList() {
        ui.displayBarrier();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currentTaskList.size(); i++) {
            System.out.println(i+1 + "." + currentTaskList.get(i).toString());
        }
        ui.displayBarrier();
    }

    public ArrayList<Task> returnList() {
        return currentTaskList;
    }

    public Task getPosValue(int posNum) {
        return currentTaskList.get(posNum);
    }


    public void addTask(Task userTask) {
        currentTaskList.add(userTask);
    }

    public void removeTask(int posNum) {
        currentTaskList.remove(posNum);
    }

    public int getSize() {
        return currentTaskList.size();
    }
}
