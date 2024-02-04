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
     * @return
     */
    public int numberOfTasks() {
        return taskList.size();
    }

    /**
     * Adds new todo to the task list and amends status
     * @param todoDescription
     * @param status
     */
    public void addNewTodo(String todoDescription, boolean status) {
        Todo todo = new Todo(todoDescription);
        todo.todoStatus = status;
        taskList.add(todo);
    }

    /**
     * Adds new todo to task list
     * @param todoDescription
     */
    public void addNewTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
    }

    /**
     * Adds new deadline to task list and amends status
     * @param deadlineDescription
     * @param endDate
     * @param status
     */
    public void addNewDeadline(String deadlineDescription, String endDate, boolean status) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        deadline.todoStatus = status;
        taskList.add(deadline);
    }

    /**
     * Adds new deadline object to task list
     * @param deadlineDescription
     * @param endDate
     */
    public void addNewDeadline(String deadlineDescription, String endDate) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        taskList.add(deadline);
    }

    /**
     * Adds new event object to task list and amends status
     * @param eventDescription
     * @param startDate
     * @param endDate
     * @param status
     */
    public void addNewEvent(String eventDescription, String startDate, String endDate, boolean status) {
        Event event = new Event(eventDescription, startDate, endDate);
        event.todoStatus = status;
        taskList.add(event);
    }

    /**
     * Adds new event object to task list
     * @param eventDescription
     * @param startDate
     * @param endDate
     */
    public void addNewEvent(String eventDescription, String startDate, String endDate) {
        Event event = new Event(eventDescription, startDate, endDate);
        taskList.add(event);
    }

    /**
     * Removes specific task from list
     * @param index
     */
    public void removeTask(int index) {
        taskList.remove(index);
    }

    /**
     * Gets specific task from list
     * @param index
     * @return
     */
    public Todo getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Returns size of task list
     * @return
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Prints the task at the end of the list
     */
    public void printNewestTask() {
        taskList.get(taskList.size() - 1).Printer();
    }

    /**
     * Prints task at specified index
     * @param index
     */
    public void printTask(int index) {
    taskList.get(index).Printer();
    }

    /**
     * Returns string representation of specific task
     * @param index
     * @return
     */
    public String stringPrintTask(int index) {
        return taskList.get(index).stringPrinter();
    }

    /**
     * Changes status of task at specified index
     * @param index
     * @param status
     */
    public void changeTaskStatus(int index, boolean status) {
        taskList.get(index).todoStatus = status;
    }

    /**
     * Returns tasklist status
     * @return
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Prints string representation of task list
     */
    public void listPrinter() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ") " + stringPrintTask(i));
        }
    }
}
