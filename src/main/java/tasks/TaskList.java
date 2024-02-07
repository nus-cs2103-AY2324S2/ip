package tasks;

import tasks.Event;
import tasks.Todo;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TaskList {

    private static String dividerText = "____________________________________________________________\n";
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> existingTaskList) {
        this.taskList = existingTaskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addTodo(String description) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        System.out.print("New todo added:\n");
        System.out.print("   " + newTodo.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    public void addDeadline(String description, LocalDate dueDate) {
        Deadline newDeadline = new Deadline(description, dueDate);
        taskList.add(newDeadline);
        System.out.print("New deadline added:\n");
        System.out.print("   " + newDeadline.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    public void addEvent(String description, LocalDate fromDate, LocalDate toDate) {
        Event newEvent = new Event(description, fromDate, toDate);
        taskList.add(newEvent);
        System.out.print("New event added:\n");
        System.out.print("   " + newEvent.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    public void deleteTask(int taskNum) {
        Task taskToBeRemoved = taskList.get(taskNum-1);
        taskList.remove(taskToBeRemoved);
        System.out.print("Task deleted:\n");
        System.out.print("   " + taskToBeRemoved.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    public void deleteAllTasks() {
        taskList.clear();
        System.out.print("All tasks deleted.\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    public void listTasks() {
        if (!this.taskList.isEmpty()) {
            for (int i = 0; i < this.taskList.size(); i++) {
                Task thisTask = taskList.get(i);
                System.out.print((i + 1) + ". " + thisTask.getTaskDetails() + "\n");
            }
        }
        else {
            System.out.print("No tasks in list.\n");
        }
        System.out.print(dividerText);
    }


    public void markTaskDone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markDone();
        String markedDoneText = "Nice! I've marked this task as done:\n";
        String taskText = "    " + thisTask.getTaskDetails() + "\n";
        System.out.print(markedDoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }

    public void markTaskUndone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markUndone();
        String markedUndoneText = "Ok, i've marked this task as not done yet:\n";
        String taskText = "    " + thisTask.getTaskDetails() + "\n";
        System.out.print(markedUndoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }

    /**
     * Returns TreeMap of tasks corresponding with the input keyword
     * as well as the index of the task in the list
     *
     * @param keyword Keyword to filter tasks
     * @return TreeMap of filtered tasks
     */
    public void findAndPrintTasks(String keyword) {
        String lowerCaseKeyword = keyword.toLowerCase();
        TreeMap<Integer, Task> filteredTasksList = new TreeMap<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task taskInList = taskList.get(i);
            if (taskInList.getDescription().toLowerCase().contains(lowerCaseKeyword)) {
                filteredTasksList.put(i + 1, taskInList);
            }
        }
        printFoundTasks(filteredTasksList);
    }

    /**
     * Prints details of tasks in treemap of filtered tasks
     *
     * @param taskTreeMap Treemap containing tasks to be printed
     */
    public void printFoundTasks(TreeMap<Integer, Task> taskTreeMap) {
        if (taskTreeMap.isEmpty()) {
            System.out.println("No tasks associated with keyword found.");
            System.out.print(dividerText);
            return;
        }

        System.out.println("Here are the matching tasks in your list: ");
        for (Map.Entry<Integer, Task> filteredTask : taskTreeMap.entrySet()) {
            int taskIndex = filteredTask.getKey();
            String taskDetails = filteredTask.getValue().getTaskDetails();
            System.out.println(taskIndex + ". " + taskDetails);
        }
        System.out.print(dividerText);
    }
}
