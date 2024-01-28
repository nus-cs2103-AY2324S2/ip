package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public ArrayList<Task> taskList;

    // duke.Todo representation - 0 means not done, 1 means done
    // T | done? | desc

    // duke.Deadline representation
    // D | done? | desc | by

    // duke.Event representation
    // E | done? | desc | from | to

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    // initialise the duke.TaskList with a List<String>
    public TaskList(List<String> stringTasksList) throws DukeException.UnknownCommandException {
        this.taskList = new ArrayList<>();
        for (String s : stringTasksList) {
            this.taskList.add(db2Task(s));
        }
    }


    /**
     * Adds a task to the current taskList
     *
     * @param task to be added
     */
    public void addTask(Task task) {
        // Based on task type, extract traits
        taskList.add(task);
    }

    /**
     * Gets a task from the current taskList
     *
     * @param task to be added
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
     * Deletes a task from the duke.TaskList, given its index
     *
     * @param index of task to delete
     */
    public void deleteTask(int index) throws DukeException.TaskNotFoundException {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            taskList.remove(index - 1);
        }
    }

    /**
     * markTask marks a task as completed by line number
     *
     * @param index of task to mark
     */
    public void markTask(int index) throws DukeException.TaskNotFoundException  {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            Task t =  this.getTask(index);
            t.markAsDone();
        }
    }

    /**
     * unmarkTask unmarks a task as completed by line number
     *
     * @param index of the task to unmark
     */
    public void unmarkTask(int index) throws DukeException.TaskNotFoundException  {
        if (index < 1 || index > taskList.size()) {
            throw new DukeException.TaskNotFoundException();
        } else {
            Task t =  this.getTask(index);
            t.unmarkAsDone();
        }
    }

    /**
     * Prints out the contents of the tasklist
     */
    public void printTasks() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + this.taskList.get(i));
        }
    }

    /**
     * Gets number of tasks
     * @return int number of tasks
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Converts the database representation of a duke.Task to the duke.Task object
     *
     * @param dbTask the string rep of the duke.Task in the database
     * @return duke.Task the duke.Task object
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
     * Converts a duke.Task to their database representation
     *
     * @param task the duke.Task object
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
}