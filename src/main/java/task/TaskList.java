package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public int size() {
        return taskList.size();
    }
    
    public void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        printMarkedTask(task);
    }

    public void unmarkTask(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkAsDone();
        printUnmarkedTask(task);
    }

    public void deleteTask(int index) {
        Task deletedTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        printDeletedTask(deletedTask);
    }

    public void addToDoToList(String taskDescription) {
        Task newTask = new ToDo(taskDescription);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void addDeadlineToList(String taskDescription, String by) {
        Task newTask = new Deadline(taskDescription, by);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void addEventToList(String taskDescription, String from, String to) {
        Task newTask = new Event(taskDescription, from, to);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void printNewTask(Task newTask) {
        String message =
                String.format("\tGot it. I've added this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        newTask, taskList.size());
        System.out.println(message);
    }

    public void printDeletedTask(Task deletedTask) {
        String message =
                String.format("\tNoted. I've remeowved this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        deletedTask, taskList.size());
        System.out.println(message);
    }
    
    public void printMarkedTask(Task markedTask) {
        System.out.println("\tAmeowzing! I've marked this task as done:\n\t" + markedTask);
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + unmarkedTask);
    }

    public void printList() {
        String listString = "";
        for (int i = 1; i <= taskList.size(); i++) {
            listString += "\t";
            listString += i + ". " + taskList.get(i - 1);
            if (i < taskList.size()) {
                listString += "\n";
            }
        }
        System.out.println(listString);
    }

}
