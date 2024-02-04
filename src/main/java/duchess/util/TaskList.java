package duchess.util;

import duchess.tasks.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void printTaskList() {
        System.out.println("\t\tHere are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i - 1);
            System.out.println("\t\t" + i + "." + t);
        }
    }

    public ArrayList<Task> retrieveTaskList() {
        return this.taskList;
    }

    public void deleteTask(int id) throws IndexOutOfBoundsException{
        String lineBreak = "\t\t------------------------------------------";
        Task t = taskList.remove(id - 1);
        System.out.println(lineBreak);
        System.out.println("\t\tNoted. I've removed this task:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }

    public void createTask(Task task) {
        taskList.add(task);
        System.out.println("\t\tGot it. I've added this task: \n\t\t  " + task);
        System.out.println("\t\tNow you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t\t------------------------------------------");
    }

    public void unmarkTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsUndone();
        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tOK, I've marked this task as not done yet:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\t------------------------------------------");
    }

    public void markTask(int id) {
        Task t = taskList.get(id - 1);
        t.markAsDone();
        System.out.println("\t\t------------------------------------------");
        System.out.println("\t\tNice! I've marked this task as done:");
        System.out.println("\t\t  " + t);
        System.out.println("\t\t------------------------------------------");
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }
}
