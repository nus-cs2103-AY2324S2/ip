package utilities;

import tasks.Task;
import utilities.Storage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskArrayList = taskList;
    }

    public void add(Task newTask) {
        this.taskArrayList.add(newTask);
        MessagePrinter.commandPrint(newTask, this.taskArrayList.size());
    }

    public void printList() {
        MessagePrinter.commandListPrint(this.taskArrayList);
    }

    public void changeStatusOfItem(String action, int which) {
        this.taskArrayList.get(which).changeStatus(action);
    }

    public void removeIndex(int index) {
        MessagePrinter.removePrinter(this.taskArrayList.get(index), this.taskArrayList.size());
        this.taskArrayList.remove(index);
    }

    public void writeToFile(Storage td) {
        td.writeToTaskList(this.taskArrayList);
    }

    @Override
    public String toString() {
        return "Now you have " + taskArrayList.size() + "tasks in the list.";
    }
}
