package tasklist;
import jux.JuxException;
import parser.Parser;
import ui.Ui;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;
    private int size = 0;
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
        size = (taskList.size());
    }

    public int getSize() {
        return size;
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    public boolean isIndexedTaskChecked(int num) {
        return taskList.get(num).getStatusIcon().equals("X");
    }
    public void toggleIndexedTask(int num) {
        taskList.get(num).toggleIsDone();
    }
    public void printTaskMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        ui.printTaskMarked(task.toString());
    }
    public void printTaskUnMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        ui.printTaskUnMarked(task.toString());
    }
    public void deleteTask(Ui ui, int num) {
        Task task = taskList.get(num);
        taskList.remove(num);
        String taskString = task.toString();
        ui.printDeletedTask(taskString);
        if (taskList.isEmpty()) {
            ui.printEmptyTaskList();
        } else {
            ui.printNumberOfTasks(getSize());
        }
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public boolean isEmpty(){

        return size == 0;
    }
    public void addTask(Ui ui, String typeOfTask, String task) throws JuxException {
        if (typeOfTask.equals(Parser.TODO)) {
            addTodo(task);
        } else if (typeOfTask.equals(Parser.DEADLINE)) {
            addDeadline(task);

        } else if (typeOfTask.equals(Parser.EVENT)){
            addEvent(task);
        } else {
            throw new JuxException("SORRY I DO NOT KNOW WHAT THAT MEANS, PLEASE TRY AGAIN!");
        }
        ui.printTaskAfterword(task);
        ui.printNumberOfTasks(getSize());

    }
    public void addTodo(String task) throws JuxException {
        taskList.add(new Todo(Parser.parseTodo(task)));

    }
    public void addDeadline(String task) throws JuxException {
        String[] args = Parser.parseDeadline(task);
        taskList.add(new Deadline(args[0], args[1]));
    }
    public void addEvent(String task) throws JuxException {
        String[] args = Parser.parseEvent(task);
        taskList.add(new Event(args[0],args[1],args[2]));
    }
    public void showList(Ui ui) {
        ui.printList(taskList);
    }
}
