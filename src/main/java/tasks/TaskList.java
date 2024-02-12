package tasks;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * The TaskList class is a class representing a collection of Task objects
 */
public class TaskList {

    private static String dividerText = "____________________________________________________________\n";
    private ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructor for a TaskList object, using existing list of tasks
     *
     * @param existingTaskList Existing ArrayList of Tasks
     * @return TaskList object created from existing ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> existingTaskList) {
        this.taskList = existingTaskList;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds Todo task to this TaskList object
     *
     * @param description Description of Todo task
     */
    public void addTodo(String description) {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        System.out.print("New todo added:\n");
        System.out.print("   " + newTodo.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    /**
     * Adds Deadline task to this TaskList object
     *
     * @param description Description of Deadline task
     * @param dueDate Due date of Deadline task as a LocalDate object
     */
    public void addDeadline(String description, LocalDate dueDate) {
        Deadline newDeadline = new Deadline(description, dueDate);
        taskList.add(newDeadline);
        System.out.print("New deadline added:\n");
        System.out.print("   " + newDeadline.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    /**
     * Adds Event task to this TaskList object
     *
     * @param description Description of Event task
     * @param fromDate Beginning date of Event task as a LocalDate object
     * @param toDate End date of Event task as a LocalDate object
     */
    public void addEvent(String description, LocalDate fromDate, LocalDate toDate) {
        Event newEvent = new Event(description, fromDate, toDate);
        taskList.add(newEvent);
        System.out.print("New event added:\n");
        System.out.print("   " + newEvent.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    /**
     * Deletes task from this TaskList object's ArrayList of Tasks
     *
     * @param taskNum Index of task on list
     */
    public void deleteTask(int taskNum) {
        Task taskToBeRemoved = taskList.get(taskNum-1);
        taskList.remove(taskToBeRemoved);
        System.out.print("Task deleted:\n");
        System.out.print("   " + taskToBeRemoved.getTaskDetails() + "\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    /**
     * Deletes all tasks from this TaskList object's ArrayList of Tasks
     */
    public void deleteAllTasks() {
        taskList.clear();
        System.out.print("All tasks deleted.\n");
        System.out.print("Tasks in list: " + taskList.size() + "\n");
        System.out.print(dividerText);
    }

    /**
     * Prints this TaskList object's current ArrayList of Tasks
     */
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

    /**
     * Marks specified task as complete
     *
     * @param taskNum Index of task on list
     */
    public void markTaskDone(int taskNum) {
        Task thisTask = taskList.get(taskNum-1);
        thisTask.markDone();
        String markedDoneText = "Nice! I've marked this task as done:\n";
        String taskText = "    " + thisTask.getTaskDetails() + "\n";
        System.out.print(markedDoneText);
        System.out.print(taskText);
        System.out.print(dividerText);
    }

    /**
     * Marks specified task as incomplete
     *
     * @param taskNum Index of task on list
     */
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
