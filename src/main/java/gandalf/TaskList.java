package gandalf;

import java.util.ArrayList;

/**
 * Class to handle operations that changes the length of the list,ie. add or delete
 */
public class TaskList {
    ArrayList<Task> list;

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
     * Adds a task, for any type, into the Arraylist.
     *
     * @param taskType
     * @param taskName
     * @param date1
     * @param date2
     */
    public void add(String taskType, String taskName, String date1, String date2) throws GandalfException {
        if(taskType.equals("todo")) {
            Task currentTask = new ToDos(taskName);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
        else if(taskType.equals("deadline")) {
            Task currentTask = new Deadlines(taskName, date1);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
        else if(taskType.equals("event")) {
            Task currentTask = new Events(taskName, date1, date2);
            this.list.add(currentTask);
            System.out.println("added new task: " + currentTask);
        }
        else{
            assert(!taskType.equals("todo"));
            throw new GandalfException("I do not recognize this command");
        }
    }
    /**
     * Given the number, delete the corresponding task on the list.
     *
     * @param taskName
     */
    public void delete(String taskName) throws GandalfException {
        int deleteNumber = Integer.parseInt(taskName);
        if(deleteNumber > list.size()) {
            throw new GandalfException("This task does not exist");
        }
        assert(deleteNumber <= list.size());
        System.out.println("removed task: " + this.list.get(deleteNumber - 1));
        this.list.remove(deleteNumber - 1);
        System.out.println("Total number of tasks so far: " + this.list.size());
    }

    public void mark(int taskIndex) {
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(true);
        assert(correspondingTask.getStatus());
        System.out.println(correspondingTask);
    }

    public void unmark(int taskIndex) {
        Task correspondingTask = list.get(taskIndex - 1);
        correspondingTask.markStatus(false);
        assert(!correspondingTask.getStatus());
        System.out.println(correspondingTask);
    }
}

