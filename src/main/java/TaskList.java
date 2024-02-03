import java.util.ArrayList;
public class TaskList {
    private ArrayList<Todo> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Todo>();
    }
    public int numberOfTasks() {
        return taskList.size();
    }
    public void addNewTodo(String todoDescription, boolean status) {
        Todo todo = new Todo(todoDescription);
        todo.todoStatus = status;
        taskList.add(todo);
    }
    public void addNewTodo(String todoDescription) {
        Todo todo = new Todo(todoDescription);
        taskList.add(todo);
    }
    public void addNewDeadline(String deadlineDescription, String endDate, boolean status) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        deadline.todoStatus = status;
        taskList.add(deadline);
    }
    public void addNewDeadline(String deadlineDescription, String endDate) {
        Deadline deadline = new Deadline(deadlineDescription, endDate);
        taskList.add(deadline);
    }
    public void addNewEvent(String eventDescription, String startDate, String endDate, boolean status) {
        Event event = new Event(eventDescription, startDate, endDate);
        event.todoStatus = status;
        taskList.add(event);
    }
    public void addNewEvent(String eventDescription, String startDate, String endDate) {
        Event event = new Event(eventDescription, startDate, endDate);
        taskList.add(event);
    }
    public void removeTask(int index) {
        taskList.remove(index);
    }
    public Todo getTask(int index) {
        return taskList.get(index);
    }
    public int size() {
        return taskList.size();
    }
    public void printNewestTask() {
        taskList.get(taskList.size() - 1).Printer();
    }
    public void printTask(int index) {
    taskList.get(index).Printer();
    }
    public String stringPrintTask(int index) {
        return taskList.get(index).stringPrinter();
    }
    public void changeTaskStatus(int index, boolean status) {
        taskList.get(index).todoStatus = status;
    }
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public void listPrinter() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ") " + stringPrintTask(i));
        }
    }
}
