package Gops;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Todo> taskList;

    /**
     * Creates new task list
     */
    public TaskList() {
        this.taskList = new ArrayList<Todo>();
    }

    /**
     * Returns the number of tasks in the list
     * @return number of tasks
     */
    public int numberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds new todo to the task list and amends status
     * @param todoDescription task description
     * @param status task status
     */
    public void addNewTodo(String todoDescription, boolean status) {
        Todo todo = new Todo(todoDescription);
        todo.isDone = status;
        taskList.add(todo);
    }

    /**
     * Adds new todo to task list
     * @param todoDescription task description
     */
    public void addNewTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
    }

    /**
     * Adds new deadline to task list and amends status
     * @param deadlineDescription task description
     * @param endDate deadline date
     * @param status task status
     */
    public void addNewDeadline(String deadlineDescription, String endDate, boolean status) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        deadline.isDone = status;
        taskList.add(deadline);
    }

    /**
     * Adds new deadline object to task list
     * @param deadlineDescription task description
     * @param endDate deadline date
     */
    public void addNewDeadline(String deadlineDescription, String endDate) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        taskList.add(deadline);
    }

    /**
     * Adds new event object to task list and amends status
     * @param eventDescription task description
     * @param startDate task start date
     * @param endDate task end date
     * @param status task status
     */
    public void addNewEvent(String eventDescription, String startDate, String endDate, boolean status) {
        Event event = new Event(eventDescription, startDate, endDate);
        event.isDone = status;
        taskList.add(event);
    }

    /**
     * Adds new event object to task list
     * @param eventDescription task description
     * @param startDate task start date
     * @param endDate task end date
     */
    public void addNewEvent(String eventDescription, String startDate, String endDate) {
        Event event = new Event(eventDescription, startDate, endDate);
        taskList.add(event);
    }

    /**
     * Removes specific task from list
     * @param index task index
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Gets specific task from list
     * @param index task index
     * @return task
     */
    public Todo getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns size of task list
     * @return task list size
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Prints the task at the end of the list
     */
    public String printNewestTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("------------------------------------------------------------------\n");
        sb.append(taskList.get(taskList.size() - 1).stringPrinter());
        sb.append("\n------------------------------------------------------------------\n");
        sb.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        String confirmationMessage = sb.toString();
        return confirmationMessage;

    }

    /**
     * Prints task at specified index
     * @param index task index
     */
    public void printTask(int index) {
    taskList.get(index).Printer();
    }

    /**
     * Returns string representation of specific task
     * @param index task index
     * @return string rep of task
     */
    public String stringPrintTask(int index) {
        return taskList.get(index).stringPrinter();
    }

    /**
     * Changes status of task at specified index
     * @param index task index
     * @param status task status
     */
    public void changeTaskStatus(int index, boolean status) {
        taskList.get(index).isDone = status;
    }

    /**
     * Returns tasklist status
     * @return task list status
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Prints string representation of task list
     */
    public String listPrinter() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the tasks in your list\n");
        stringBuilder.append("------------------------------------------------------------------\n");
        for (int i = 0; i < taskList.size(); i++) {
            stringBuilder.append(i + 1).append(") ").append(stringPrintTask(i)).append("\n");
        }
        stringBuilder.append("------------------------------------------------------------------\n");
        return stringBuilder.toString();
    }
    public void add(Todo task) {
        taskList.add(task);
    }
    public TaskList findInList(String item) {
        TaskList refinedList = new TaskList();
        for (Todo task : taskList) {
            if (task.itemDescription.contains(item)) {
                refinedList.add(task);
            }
        }
        return refinedList;
    }
    public void clearList() {
        this.taskList = new ArrayList<Todo>();
    }

    public String printTaskAsNotMarked(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK! I've marked this task as not done yet:\n").append(taskList.get(index).stringPrinter());
        return sb.toString();
    }
    public String printTaskAsMarked(int index) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK! I've marked this task as done:\n").append(taskList.get(index).stringPrinter());
        return sb.toString();
    }


}
