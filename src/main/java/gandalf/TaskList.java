package gandalf;

import gandalf.tasktypes.Task;
import gandalf.tasktypes.Deadlines;
import gandalf.tasktypes.Events;
import gandalf.tasktypes.Expenses;
import gandalf.tasktypes.ToDos;

import java.util.ArrayList;

/**
 * Class to handle operations that changes the length or contents of the list,such as add or delete.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds a task, for any type, by creating the correct Task object and add it into the Arraylist.
     *
     * @param taskType specifies the Task type object to be instantiated.
     * @param taskName describes the task.
     * @param firstInfo additional info for the task, if applicable.
     * @param secondInfo additional info for the taask, if applicable.
     */
    public void add(String taskType, String taskName, String firstInfo, String secondInfo) throws GandalfException {
        Task currentTask;
        switch (taskType) {
        case "todo":
            currentTask = new ToDos(taskName);
            break;
        case "deadline":
            currentTask = new Deadlines(taskName, firstInfo);
            break;
        case "event":
            currentTask = new Events(taskName, firstInfo, secondInfo);
            break;
        case "expenses":
            currentTask = new Expenses(taskName, firstInfo);
            break;
        default:
            throw new GandalfException("I do not recognize this command");
        }
        assert(currentTask != null);
        this.list.add(currentTask);
        System.out.println("added new task: " + currentTask);
    }
    /**
     * Given the number, delete the corresponding task on the list.
     *
     * @param taskName Name of task to be deleted.
     */
    public void delete(String taskName) throws GandalfException {
        int deleteNumber = Integer.parseInt(taskName);
        if (deleteNumber > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        assert(deleteNumber <= list.size());
        System.out.println("removed task: " + this.list.get(deleteNumber - 1));
        this.list.remove(deleteNumber - 1);
        System.out.println("Total number of tasks so far: " + this.list.size());
    }

    /**
     * Lists all the tasks in the list, prints as 1-indexing.
     */
    public String list() {
        String list = "";
        for (int i = 0; i < this.list.size(); i++) {
            Task action = this.list.get(i);
            list += (i + 1) + ". " + action + "\n";
        }
        return list;
    }

    /**
     * Filters the list and returns another list containing only tasks that contains the given keyword.
     *
     * @param keyword Name to be filtered.
     * @return filtered list.
     */
    public TaskList filter(String keyword) {
        TaskList filteredList = new TaskList();
        int numOfFiltered = 0;
        for (int i = 0; i < list.size(); i++) {
            Task action = list.get(i);
            String nameOfTask = action.getNameOfTask();
            if (nameOfTask.contains(keyword)) {
                filteredList.getList().add(numOfFiltered, action);
                numOfFiltered++;
            }
            assert(i != list.size());
        }
        return filteredList;
    }

    /**
     * Checks the list for certain expenses and get the amount spent.
     *
     * @param expensesName Expenses to calculate
     * @return Total amount spent
     */
    public double sumExpenses(String expensesName) {
        double totalSum = 0;
        for (int i = 0; i < list.size(); i++) {
            Task action = list.get(i);
            if (action instanceof Expenses) {
                Expenses expenses = (Expenses) action; //due to checking using instanceof, this is safe type-casting
                if (expensesName.equals("all")) {
                    totalSum += expenses.getPrice();
                } else {
                    if (expensesName.equals(expenses.getNameOfTask())) {
                        totalSum += expenses.getPrice();
                    }
                }
            }
        }
        return totalSum;
    }

    /**
     * Marks the task at the specified index.
     *
     * @param taskIndex Index of task to be marked.
     * @throws GandalfException throws an error if index is out of bounds.
     */
    public String mark(int taskIndex) throws GandalfException {
        if (taskIndex > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(true);
        assert(correspondingTask.getStatus());
        return correspondingTask.toString();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param taskIndex Index of task to be unmarked.
     * @throws GandalfException throws an error if index is out of bounds.
     */
    public String unmark(int taskIndex) throws GandalfException {
        if (taskIndex > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(false);
        assert(!correspondingTask.getStatus());
        return correspondingTask.toString();
    }
}

