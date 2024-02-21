package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of Task objects and handles operations like getting, updating, and deleting them.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private ArrayList<Task> prevTaskList; // previous taskList
    private Task prevTask; // task from previous operation
    private ChangeOperation prevOp; // previous operation

    /**
     * Enum of operations that change taskList.
     */
    private enum ChangeOperation {
        MARK,
        UNMARK,
        ADD,
        DELETE
    }

    /**
     * Constructs a new TaskList with an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initializes the TaskManager with a list of strings representing tasks in their database representations.
     *
     * @param stringTasksList List of strings representing tasks in the database
     * @throws DukeBotException.UnknownCommandException if an unknown command is encountered while parsing the tasks
     */
    public TaskList(List<String> stringTasksList) throws DukeBotException.UnknownException {
        this.taskList = new ArrayList<>();
        assert taskList != null;
        for (String s : stringTasksList) {
            this.taskList.add(parseStringToTask(s));
        }
    }

    /**
     * Finds tasks containing a specific keyword in their description.
     * @param keyword The keyword to search for in task descriptions (case-sensitive)
     */
    public void findTasks(String keyword) {
        int num = 0;
        System.out.println("Here's what we got'");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            if (t.taskDescription.contains(keyword)) {
                System.out.println((num + 1) + ". " + t);
                num++;
            }
        }
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve
     * @return The task at the specified index
     * @throws DukeBotException.TaskException if the index is out of bounds
     */
    public Task getTask(int index) throws DukeBotException.TaskException {
        if (index >= 1 && index <= taskList.size()) {
            return taskList.get(index - 1);
        } else {
            throw new DukeBotException.TaskException();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        assert task != null;
        prevTaskList = cloneTaskList(taskList);
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of task to delete
     * @throws DukeBotException.TaskException if index is out of bounds
     */
    public void deleteTask(int index) throws DukeBotException.TaskException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeBotException.TaskException();
        } else {
            prevTaskList = cloneTaskList(taskList);
            taskList.remove(index - 1);
        }
    }

    /**
     * Marks a task as completed by its index.
     *
     * @param index The index of task to mark as completed
     * @throws DukeBotException.TaskException if index is out of bounds
     */
    public void markTask(int index) throws DukeBotException.TaskException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeBotException.TaskException();
        } else {
            prevTaskList = cloneTaskList(taskList);
            Task t = this.getTask(index);
            t.markDone();
        }
    }

    /**
     * Unmarks a task as completed by its index.
     *
     * @param index The index of task to unmark
     * @throws DukeBotException.TaskException if index is out of bounds
     */
    public void unmarkTask(int index) throws DukeBotException.TaskException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeBotException.TaskException();
        } else {
            prevTaskList = cloneTaskList(taskList);
            Task t = this.getTask(index);
            t.unmarkDone();
        }
    }

    /**
     * Reverts task list to previous state before the last change.
     */
    public void undo() {
        if (prevTaskList == null) {
            System.out.println("Undo cannot be performed. There are no tasks to undo.");
        } else {
            ArrayList<Task> tmpTaskList = cloneTaskList(taskList);
            taskList = prevTaskList;
            prevTaskList = tmpTaskList;
            System.out.println("Undo is successful!");
        }
    }

    /**
     * Prints out contents of the task list.
     */
    public void printTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Gets a task list.
     *
     * @return The tasks in the task list
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Converts the database representation of a task to a Task object.
     * @param dbTask The string representation of the task in the database
     * @return The Task object
     * @throws DukeBotException.UnknownException if an unknown command is encountered while parsing the task
     */

    public static Task parseStringToTask(String dbTask) throws DukeBotException.UnknownException {
        String[] param = dbTask.split(" \\| ");
        String type = param[0];
        switch (type) {
        case "T": // To do
            Todo todoTask = Todo.db2Todo(dbTask);
            return todoTask;
        case "D": // Deadline
            DeadlineTask deadlineTask = DeadlineTask.dbToDeadlineTask(dbTask);
            return deadlineTask;
        case "E": // Event
            EventTask eventTask = EventTask.dbToEventTask(dbTask);
            return eventTask;
        default:
            System.out.println("Unrecognized task type");
            throw new DukeBotException.UnknownException();
        }
    }

    /**
     * Converts a Task to its database representation.
     *
     * @param task The Task object
     * @return The string representation of the Task in the database
     */
    public static String taskToDbString(Task task) {
        if (task instanceof Todo) {
            Todo todoTask = (Todo) task;
            return Todo.todo2Db(todoTask);
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return DeadlineTask.deadlineTaskToDb(deadlineTask);
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return EventTask.eventTaskToDb(eventTask);
        } else {
            System.out.println("Could not add: " + task);
            return null;
        }
    }

    /**
     * Clones the task list for saving the previous state in case of an undo operation.
     *
     * @param taskList The task list to clone
     * @return A deep copy of the task list
     */
    private ArrayList<Task> cloneTaskList(ArrayList<Task> taskList) {
        ArrayList<Task> newPrevTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todoTask = (Todo) task;
                newPrevTaskList.add(new Todo(todoTask));
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                newPrevTaskList.add(new DeadlineTask(deadlineTask));
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                newPrevTaskList.add(new EventTask(eventTask));
            } else {
                System.out.println("Could not add: " + task);
                return null;
            }
        }
        return newPrevTaskList;
    }
}
