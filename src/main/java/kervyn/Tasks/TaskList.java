package kervyn.Tasks;

import java.util.ArrayList;

public class TaskList {
    // Contains task-related operations
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList (ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    // List tasks
    public short listTasks(ArrayList<Task> userTasks) {
        System.out.println("\tHere are the tasks on your list:");
        for (int i = 0; i < userTasks.size(); i++) {
            Task task = userTasks.get(i);
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            switch (type) {
                case 'T':
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " +  "[" + check + "] " + task.getDescription());
                    return 1;
                case 'D':
                    Deadline deadlineTask = (Deadline) task;
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] " + deadlineTask.getDescription() + " (by: " + deadlineTask.getDeadline() + ")");
                    return 1;
                case 'E':
                    Event eventTask = (Event) task;
                    System.out.println("\t" + (i + 1) + "." + "[" + type + "] " + "[" + check + "] "  + eventTask.getDescription() + " (from: " + eventTask.getStartDate() + " to: " + eventTask.getEndDate() + ")");
                    return 1;
            }
        }
        return 0;
    }

    // mark task
    public short markTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (task.getStatus()) {
                taskAlreadyMarked();
            } else {
                System.out.println("\tNice! I've marked this task as done:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to mark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
        return 0;
    }

    // Unmark task
    public short unMarkTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            if (!task.getStatus()) {
                taskAlreadyUnMarked();
            } else {
                System.out.println("\tOK, I've marked this task as not done yet:");
                task.updateStatus();
                char check = task.getStatus() ? 'X' : ' ';
                char type = task.getCapitalType();
                System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            }

            return 1;
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to unmark a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
        return 0;
    }

    private static void taskAlreadyMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as done, please try again with another task!");
    }

    private static void taskAlreadyUnMarked() {
        System.out.println("\tUh oh! It looks like this task is already marked as not done, please try again with another task!");
    }

    // Delete task
    public void removeTask(ArrayList<Task> userTasks, String[] processedUserInput) {
        try {
            Task task = userTasks.get(Integer.parseInt(processedUserInput[1]) - 1);
            System.out.println("\tOK, I've removed this task as per your request:");
            char check = task.getStatus() ? 'X' : ' ';
            char type = task.getCapitalType();
            System.out.println("\t[" + type + "] " + "[" + check + "] " + task.getDescription());
            userTasks.remove(task);
        }
        catch (IndexOutOfBoundsException e) {
            // Need to account for trying to delete a task that doesn't exist
            System.out.println("\tTask number provided doesn't exist. Please try again.");
        }
    }
}
