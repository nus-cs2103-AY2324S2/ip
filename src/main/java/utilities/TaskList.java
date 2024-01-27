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

    public void findTasks(String taskPattern) {
        ArrayList<Task> tasksWithPattern = new ArrayList<>();
        for (int i = 0; i < this.taskArrayList.size(); i += 1) {
            String currTaskName = taskArrayList.get(i).getTaskName();
            if (currTaskName.contains(taskPattern)) {
                tasksWithPattern.add(taskArrayList.get(i));
            }
        }
        MessagePrinter.printFoundTasks(tasksWithPattern);
    }

    @Override
    public String toString() {
        return "Now you have " + taskArrayList.size() + "tasks in the list.";
    }
}
