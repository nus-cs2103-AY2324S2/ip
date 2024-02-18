package aaron.task;

import java.util.ArrayList;

import aaron.exception.AaronBotException;
import aaron.exception.DoubleMarkException;
import aaron.exception.DuplicateTaskException;
import aaron.exception.InvalidTaskTypeException;
import aaron.exception.TaskErrorException;
import aaron.exception.TaskListOutOfBoundsException;
import aaron.ui.UI;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Method to show list in string format
     * 
     * @return string representation of task list
     */
    public String showList() {
        String taskListString = "";
        for (int x = 0; x < tasks.size(); x++) {
            taskListString += ("\n" + (x + 1) + ". " + tasks.get(x));
        }
        return taskListString;
    }

    public void addToList(TaskType taskType, String newTask) throws TaskErrorException {
        Task task;
        switch (taskType) {
            case TODO:
                task = addTodo(newTask);
                break;
            case DEADLINE:
                task = addDeadline(newTask);
                break;
            case EVENT:
                task = addEvent(newTask);
                break;
            default:
                throw new InvalidTaskTypeException("Invalid task type: " + taskType.toString());
        }
        if (tasks.contains(task)) {
            throw new DuplicateTaskException("This task is already in the tasklist student");
        }
        tasks.add(task);
    }

    /**
     * Helper method to add todo
     * @param newTask string
     * @return todo task
     * @throws TaskErrorException error for task instantiation
     */
    private Todo addTodo(String newTask) throws TaskErrorException {
        try {
            return new Todo(newTask);
        } catch (AaronBotException e) {
            throw new TaskErrorException(e.getMessage());
        }
    }

    /**
     * Helper method to add deadline
     * @param newTask string
     * @return deadline task
     * @throws TaskErrorException error making task
     */
    private Deadline addDeadline(String newTask) throws TaskErrorException {
        String[] tokenizedTask = newTask.split(" /by ", 2);
        if (tokenizedTask.length < 2) {
            throw new TaskErrorException("Invalid format: " + newTask);
        }
        try {
            return new Deadline(tokenizedTask[0], tokenizedTask[1]);
        } catch (AaronBotException e) {
            throw new TaskErrorException(e.getMessage());
        }
    }

    /**
     * Helper method to add event
     * @param newTask string
     * @return event task
     * @throws TaskErrorException error making task
     */
    private Event addEvent(String newTask) throws TaskErrorException {
        String[] taskTimingSplit = newTask.split(" /from ", 2);
        if (taskTimingSplit.length < 2) {
            throw new TaskErrorException("Invalid format: " + newTask);
        }
        String[] startEndSplit = taskTimingSplit[1].split(" /to ", 2);
        if (startEndSplit.length < 2) {
            throw new TaskErrorException("Invalid format: " + newTask);
        }
        try {
            return new Event(taskTimingSplit[0], startEndSplit[0], startEndSplit[1]);
        } catch (AaronBotException e) {
            throw new TaskErrorException(e.getMessage());
        }
    }

    /**
     * Method to mark a task in tasklist
     * @param index of tasklist to be marked
     * @throws AaronBotException if error with marking
     */
    public void mark(int index) throws AaronBotException {
        Task task;
        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException("out of bounds index: " + index);
        }
        try {
            task.markDone();
        } catch (DoubleMarkException e) {
            throw e;
        }
    }

    /**
     * Method to unmark a task in tasklist
     * @param index of tasklist to be unmarked
     * @throws AaronBotException if error with unmarking
     */
    public void unmark(int index) throws AaronBotException {
        Task task;
        try {
            task = tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException("out of bounds index: " + index);
        }
        try {
            task.unmarkDone();
        } catch (DoubleMarkException e) {
            throw e;
        }
    }

    /**
     * Method to delete task from list
     * @param index of task to be deleted
     * @throws TaskListOutOfBoundsException if index out of bounds
     */
    public void deleteTask(int index) throws TaskListOutOfBoundsException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException("out of bounds index: " + index);
        }
    }

    /**
     * Method to print a specific task from list
     * @param index of task to print
     * @return task printed
     */
    public String printTask(int index) {
        return tasks.get(index - 1).toString();
    }

    /**
     * Method to get length of task list
     * @return length of task list
     */
    public int getTasklistSize() {
        return tasks.size();
    }

    /**
     * Method to show all tasks in tasklist with selected user input
     * 
     * @param userInput user input
     * @param ui        UI to handle user interaction
     */
    public String showKeywordTasklist(String userInput, UI ui) {
        TaskList tempTaskList = new TaskList();
        for (Task task : tasks) {
            if (task.searchWord(userInput)) {
                tempTaskList.tasks.add(task);
            }
        }
        return tempTaskList.showList();
    }

}
