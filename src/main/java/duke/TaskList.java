package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of Task objects and CRUD operations
 */

public class TaskList {
    private ArrayList<Task> taskList;
    private ArrayList<Task> priorTaskList; // previous taskList for undo method
    private Task priorTask; // task from previous operation
    private ChangeOperation priorOp; // previous operation

    /**
     * Operations that affect the taskList.
     */
    private enum ChangeOperation {
        MARK,
        UNMARK,
        ADD,
        DELETE
    }

    /**
     * Creates a new TaskList (empty)
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates the TaskList with list of tasks as strings to be stored in database
     *
     * @param stringTasksList List of strings representing tasks in storage
     * @throws DukeBotException.UnknownCommandException if an unknown command is encountered
     */
    public TaskList(List<String> stringTasksList) throws DukeBotException.UnknownException {
        this.taskList = new ArrayList<>();
        assert taskList != null;
        for (String s : stringTasksList) {
            this.taskList.add(parseStringToTask(s));
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task that is to be added
     */
    public void addTask(Task task) {
        assert task != null;
        priorTaskList = copyTaskList(taskList);
        taskList.add(task);
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve
     * @return The task at the specified index
     * @throws DukeBotException.TaskException if the index is out of bounds (does not exist)
     */
    public Task getTask(int index) throws DukeBotException.TaskException {
        if (index >= 1 && index <= taskList.size()) {
            return taskList.get(index - 1);
        } else {
            throw new DukeBotException.TaskException();
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
     * Prints out contents of the task list.
     */
    public void printTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * Deletes a task from the task list by index.
     *
     * @param index The index of task to delete
     * @throws DukeBotException.TaskException if index is out of bounds
     */
    public void deleteTask(int index) throws DukeBotException.TaskException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeBotException.TaskException();
        } else {
            priorTaskList = copyTaskList(taskList);
            taskList.remove(index - 1);
        }
    }

    /**
     * Find operation - to find tasks containing a specific keyword
     * @param keyword Specific word to search for
     */
    public void findTasks(String keyword) {
        int counter = 0;
        System.out.println("Here's what we got");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            if (t.taskDescription.contains(keyword)) {
                System.out.println((counter + 1) + ". " + t); //Formatting
                counter++;
            }
        }
    }

    /**
     * Marks a task as completed by index.
     *
     * @param index The index of task to mark as completed
     * @throws DukeBotException.TaskException if index is out of bounds
     */
    public void markTask(int index) throws DukeBotException.TaskException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeBotException.TaskException();
        } else {
            priorTaskList = copyTaskList(taskList);
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
            priorTaskList = copyTaskList(taskList);
            Task t = this.getTask(index);
            t.unmarkDone();
        }
    }

    /**
     * Reverts task list to previous state (undo operation)
     */
    public void undo() {
        if (priorTaskList == null) {
            System.out.println("No tasks to undo!");
        } else {
            ArrayList<Task> markedTaskList = copyTaskList(taskList);
            taskList = priorTaskList;
            priorTaskList = markedTaskList;
            System.out.println("Undo is successful!");
        }
    }


    /**
     * Converts a Task to its database form.
     *
     * @param task The Task object
     * @return The string form of the Task in the database
     */

    //Use of instanceof inspired by team member code
    public static String taskToDbString(Task task) {
        if (task instanceof Todo) {
            Todo todoTask = (Todo) task;
            return Todo.todoStorage(todoTask);
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return DeadlineTask.outputDeadline(deadlineTask);
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return EventTask.outputEvent(eventTask);
        } else {
            System.out.println("Could not add: " + task);
            return null;
        }
    }

    /**
     * Converts the stored task to a Task object.
     * @param dbTask Stored form of the task
     * @return The Task object
     * @throws DukeBotException.UnknownException if an unknown command is encountered
     */
    public static Task parseStringToTask(String dbTask) throws DukeBotException.UnknownException {
        String[] param = dbTask.split(" \\| ");
        String type = param[0];
        switch (type) {
        case "T": // To do
            Todo todoTask = Todo.todoOutput(dbTask);
            return todoTask;
        case "E": // Event
            EventTask eventTask = EventTask.storageEvent(dbTask);
            return eventTask;
        case "D": // Deadline
            DeadlineTask deadlineTask = DeadlineTask.storageDeadline(dbTask);
            return deadlineTask;
        default:
            System.out.println("Unrecognized task type");
            throw new DukeBotException.UnknownException();
        }
    }

    /**
     * Duplicates the task list to save the previous state (undo operation).
     *
     * @param taskList The task list to copy
     * @return Copy of the task list
     */

    //Use of instanceof inspired by team member code
    private ArrayList<Task> copyTaskList(ArrayList<Task> taskList) {
        ArrayList<Task> newPriorTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todoTask = (Todo) task;
                newPriorTaskList.add(new Todo(todoTask));
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                newPriorTaskList.add(new DeadlineTask(deadlineTask));
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                newPriorTaskList.add(new EventTask(eventTask));
            } else {
                System.out.println("Could not add: " + task);
                return null;
            }
        }
        return newPriorTaskList;
    }
}
