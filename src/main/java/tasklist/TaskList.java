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

    /**
     * Returns whether the task is checked
     * @param num index of task
     * @return boolean value of whether the task is checked
     */
    public boolean isIndexedTaskChecked(int num) {
        return taskList.get(num).getStatusIcon().equals("X");
    }

    /**
     * Toggles whether the indexed task is marked
     * @param num index of task
     */
    public void toggleIndexedTask(int num) {
        taskList.get(num).toggleIsDone();
    }

    /**
     * Prints the task when it is marked
     * @param ui the ui
     * @param num the index for the task
     */
    public void printTaskMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        ui.printTaskMarked(task.toString());
    }

    /**
     * Prints the task when it is unmarked
     * @param ui the ui
     * @param num the index for the task
     */
    public void printTaskUnMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        ui.printTaskUnMarked(task.toString());
    }

    /**
     * Remove indexed task from tasklist and
     * print to user
     * @param ui the ui
     * @param num the index of the task
     */
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

    /**
     * Add the corresponding task to the tasklist
     * @param ui the ui
     * @param typeOfTask the type of task
     * @param task the task information to add
     * @throws JuxException
     */
    public void addTask(Ui ui, String typeOfTask, String task) throws JuxException {
        if (typeOfTask.equals(Parser.TASK_TODO)) {
            addTodo(task);
        } else if (typeOfTask.equals(Parser.TASK_DEADLINE)) {
            addDeadline(task);

        } else if (typeOfTask.equals(Parser.TASK_EVENT)){
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
    public void showListWithIndexing(Ui ui) {
        ui.printListWithIndexing(taskList);
    }
}
