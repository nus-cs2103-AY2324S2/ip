package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of Task objects, and handles operations like getting, updating and deleting them.
 */
public class TaskList {
    public ArrayList<Task> taskList;
    public ArrayList<Task> prevTaskList; // contains the taskList before mutation
    private Task prevTask; // contains the task involved in the previous operation
    private MutateOps prevOp; // contains the previous operation that mutated taskList

    /**
     * Enum of operations that can mutate taskList
     */
    private enum MutateOps {
        MARK,
        UNMARK,
        ADD,
        DELETE
    }

    // duke.Todo representation - 0 means not done, 1 means done
    // T | done? | desc

    // duke.Deadline representation
    // D | done? | desc | by

    // duke.Event representation
    // E | done? | desc | from | to

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises the TaskList, given a list of strings of tasks in their database representations.
     */
    public TaskList(List<String> stringTasksList) throws DukeException.UnknownCommandException {
        this.taskList = new ArrayList<>();
        assert taskList != null;
        for (String s : stringTasksList) {
            this.taskList.add(db2Task(s));
        }
    }

    /**
     * Gives users a way to find a task by searching for a keyword.
     * @param keyword to search for in description, is case-sensitive
     */
    public void findTasks(String keyword) {
        int num = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            if (t.description.contains(keyword)) {
                System.out.println((num + 1) + ". " + t);
                num++;
            }
        }
    }

    /**
     * Gets a task from the current taskList.
     *
     * @param index of task to get
     */
    public Task getTask(int index) throws DukeException.TaskNotFoundException {
        // Check if the index is within the valid range
        if (index >= 1 && index <= taskList.size()) {
            return taskList.get(index - 1);
        } else {
            throw new DukeException.TaskNotFoundException();
        }
    }

    /**
     * Adds a task to the current taskList.
     *
     * @param task to be added
     */
    public void addTask(Task task) {
        // Based on task type, extract traits
        assert task != null;

        // update prevTaskList
        prevTaskList = cloneTaskList(taskList);

        taskList.add(task);
    }

    /**
     * Deletes a task from the duke.TaskList, given its index.
     *
     * @param index of task to delete
     */
    public void deleteTask(int index) throws DukeException.TaskNotFoundException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            // update prevTaskList
            prevTaskList = cloneTaskList(taskList);

            taskList.remove(index - 1);
        }
    }

    /**
     * markTask marks a task as completed by line number.
     *
     * @param index of task to mark
     */
    public void markTask(int index) throws DukeException.TaskNotFoundException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            // update prevTaskList
            prevTaskList = cloneTaskList(taskList);

            Task t = this.getTask(index);
            t.markAsDone();
        }
    }

    /**
     * unmarkTask unmarks a task as completed by line number.
     *
     * @param index of the task to unmark
     */
    public void unmarkTask(int index) throws DukeException.TaskNotFoundException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            // update prevTaskList
            prevTaskList = cloneTaskList(taskList);

            Task t = this.getTask(index);
            t.unmarkAsDone();
        }
    }

    /**
     * Reverts taskList to prevTaskList
     */
    public void undo() {
        if (prevTaskList == null) {
            System.out.println("Undo unsuccessful, no previous actions to undo!");
        } else {
            ArrayList<Task> tmpTaskList = cloneTaskList(taskList);
            taskList = prevTaskList;
            prevTaskList = tmpTaskList;
            System.out.println("Undo Successful!");
        }
    }

    /**
     * Prints out the contents of the tasklist.
     */
    public void printTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * Gets number of tasks.
     * @return int number of tasks
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Converts the database representation of a Task to the Task object.
     *
     * @param dbTask the string rep of the duke.Task in the database
     * @return Task the duke.Task object
     */
    public static Task db2Task(String dbTask) throws DukeException.UnknownCommandException {
        String[] params = dbTask.split(" \\| ");
        String type = params[0];
        switch (type) {
        case "T": //  To do
            Todo todoTask = Todo.db2Todo(dbTask);
            return todoTask;
        case "D": // duke.Deadline
            Deadline deadlineTask = Deadline.db2Deadline(dbTask);
            return deadlineTask;
        case "E": // duke.Event
            Event eventTask = Event.db2Event(dbTask);
            return eventTask;
        default:
            System.out.println("Failed to convert string to task!");
            throw new DukeException.UnknownCommandException();
        }
    }

    /**
     * Converts a Task to their database representation.
     *
     * @param task the Task object
     * @return String the string rep of duke.Task in the database
     */
    public static String task2Db(Task task) {
        // Based on task type, extract traits
        if (task instanceof Todo) {
            Todo todoTask = (Todo) task;
            return Todo.todo2Db(todoTask);
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            return Deadline.deadline2Db(deadlineTask);
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            return Event.event2Db(eventTask);
        } else {
            System.out.println("Failed to add task: " + task);
            return null;
        }
    }

    /**
     * Clones taskList, for saving the previous state in case of an undo
     * @param taskList to deep copy
     * @return A deep copy of the task list
     */
    private ArrayList<Task> cloneTaskList(ArrayList<Task> taskList) {
        ArrayList<Task> newPrevTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todoTask = (Todo) task;
                newPrevTaskList.add(new Todo(todoTask));
            } else if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                newPrevTaskList.add(new Deadline(deadlineTask));
            } else if (task instanceof Event) {
                Event eventTask = (Event) task;
                newPrevTaskList.add(new Event(eventTask));
            } else {
                System.out.println("Failed to add task: " + task);
                return null;
            }
        }
        return newPrevTaskList;
    }
}
