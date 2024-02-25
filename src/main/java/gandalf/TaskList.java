package gandalf;

import java.util.ArrayList;

/**
 * Class to handle operations that changes the length or contents of the list,such as add or delete
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
     * @param taskType
     * @param taskName
     * @param startDate
     * @param endDate
     */
    public void add(String taskType, String taskName, String startDate, String endDate) throws GandalfException {
        Task currentTask;
        switch (taskType) {
        case "todo":
            currentTask = new ToDos(taskName);
            break;
        case "deadline":
            currentTask = new Deadlines(taskName, startDate);
            break;
        case "event":
            currentTask = new Events(taskName, startDate, endDate);
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
     * @param taskName
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
     * Lists all the tasks in the list, prints as 1-indexing
     */
    public void list() {
        for (int i = 0; i < this.list.size(); i++) {
            Task action = this.list.get(i);
            System.out.println((i + 1) + ". " + action);
        }
    }

    /**
     * Filters the list and returns another list containing only tasks that contains the given keyword.
     *
     * @param keyword
     * @return filtered list
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
     * Marks the task at the specified index.
     *
     * @param taskIndex
     * @throws GandalfException
     */
    public void mark(int taskIndex) throws GandalfException {
        if (taskIndex > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(true);
        assert(correspondingTask.getStatus());
        System.out.println(correspondingTask);
    }

    /**
     * Unmarks the task at the specified index
     *
     * @param taskIndex
     * @throws GandalfException
     */
    public void unmark(int taskIndex) throws GandalfException {
        if (taskIndex > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(false);
        assert(!correspondingTask.getStatus());
        System.out.println(correspondingTask);
    }
}

