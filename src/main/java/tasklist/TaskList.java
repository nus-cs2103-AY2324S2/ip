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

    /**
     * Updates the size of array and
     * returns the size of array
     * @return size  of array
     */
    public int getSize() {
        size = taskList.size();
        return size;
    }
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Finds tasks with description containing the string to find
     * @param taskToFind the user input string of task
     * @return an arraylist of the task that matches
     */
    public ArrayList<Task> findTask(String taskToFind) {
        ArrayList<Task> taskArray = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            if (taskList.get(i).match(taskToFind)) {
                taskArray.add(taskList.get(i));
            }
        }
        return taskArray;

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
     * @return the marked task string
     */
    public String printTaskMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        assert task.getStatusIcon().equals("X"): "task should already be marked" ;
        return ui.printTaskMarked(task.toString());
    }

    /**
     * Prints the task when it is unmarked
     * @param ui the ui
     * @param num the index for the task
     * @return the unmarked task string
     */
    public String printTaskUnMarked(Ui ui, int num) {
        Task task = taskList.get(num);
        assert task.getStatusIcon().equals(" "): "task should be unmarked" ;
        return ui.printTaskUnMarked(task.toString());
    }

    /**
     * Remove indexed task from tasklist and
     * print to user
     * @param ui the ui
     * @param num the index of the task
     * @return the deleted task
     */
    public String deleteTask(Ui ui, int num) {
        Task task = taskList.get(num);
        taskList.remove(num);
        String taskString = task.toString();
        String output = ui.printDeletedTask(taskString);
        if (taskList.isEmpty()) {
            output += "\n" + ui.printEmptyTaskList();
        } else {
            output += "\n" + ui.printNumberOfTasks(getSize());
        }
        return output;
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
     * @return the task added
     * @throws JuxException if invalid input
     */
    public String addTask(Ui ui, String typeOfTask, String task) throws JuxException {
        if (typeOfTask.equals(Parser.TASK_TODO)) {
            addTodo(task);
        } else if (typeOfTask.equals(Parser.TASK_DEADLINE)) {
            addDeadline(task);
        } else if (typeOfTask.equals(Parser.TASK_EVENT)){
            addEvent(task);
        } else {
            throw new JuxException("SORRY I DO NOT KNOW WHAT THAT MEANS, PLEASE TRY AGAIN!");
        }
        String output = ui.printTaskAfterword(task);
        output += "\n" + ui.printNumberOfTasks(this.getSize());
        return output;

    }
    public void addTodo(String task) throws JuxException {
        taskList.add(new Todo(Parser.parseTodo(task)));

    }
    public void addDeadline(String task) throws JuxException {
        String[] args = Parser.parseDeadline(task);
        assert args.length == 2 : "Deadline should have description and time!";
        taskList.add(new Deadline(args[0], args[1]));
    }
    public void addEvent(String task) throws JuxException {
        String[] args = Parser.parseEvent(task);
        assert args.length == 2 : "Event should have description and time range!";
        taskList.add(new Event(args[0],args[1],args[2]));
    }
    public String showListWithIndexing(Ui ui) {
        return ui.printListWithIndexing(taskList);
    }
}
