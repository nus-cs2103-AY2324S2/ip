package aaron.task;

import java.util.ArrayList;

import aaron.exception.AaronBotException;
import aaron.exception.DoubleMarkException;
import aaron.exception.DuplicateTaskException;
import aaron.exception.InvalidDateException;
import aaron.exception.InvalidTaskTypeException;
import aaron.exception.TaskErrorException;
import aaron.exception.TaskListAddException;
import aaron.exception.TaskListOutOfBoundsException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    public void showList() {
        for (int x = 0; x < tasks.size(); x++) {
            System.out.println((x + 1) + ". " + tasks.get(x));
        }
    }

    public void addToList(TaskType taskType, String newTask) throws TaskErrorException {
        Task task;
        switch (taskType) {
            case TODO:
                try {
                    task = new Todo(newTask);
                } catch (AaronBotException e) {
                    throw new TaskErrorException(e.getMessage());
                }
                break;
            case DEADLINE:
                String[] tokenizedTask = newTask.split(" /by ", 2);
                if (tokenizedTask.length < 2) {
                    throw new TaskErrorException("Invalid format: " + newTask);
                }
                try {
                    task = new Deadline(tokenizedTask[0], tokenizedTask[1]);
                } catch (AaronBotException e) {
                    throw new TaskErrorException(e.getMessage());
                }
                break;
            case EVENT:
                String[] taskTimingSplit = newTask.split(" /from ", 2);
                if (taskTimingSplit.length < 2) {
                    throw new TaskErrorException("Invalid format: " + newTask);
                }
                String[] startEndSplit = taskTimingSplit[1].split(" /to ", 2);
                if (startEndSplit.length < 2) {
                    throw new TaskErrorException("Invalid format: " + newTask);
                }
                try {
                    task = new Event(taskTimingSplit[0], startEndSplit[0], startEndSplit[1]);
                } catch (AaronBotException e) {
                    throw new TaskErrorException(e.getMessage());
                }
                break;
            default:
                throw new InvalidTaskTypeException("Invalid task type: " + taskType.toString());
        }
        if (tasks.contains(task)) {
            throw new DuplicateTaskException("This task is already in the tasklist student");
        }
        tasks.add(task);
    }

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

    public void deleteTask(int index) throws TaskListOutOfBoundsException {
        try {
            tasks.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListOutOfBoundsException("out of bounds index: " + index);
        }
    }

    public String printTask(int index) {
        return tasks.get(index - 1).toString();
    }

    public int getTasklistSize() {
        return tasks.size();
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        try {
            taskList.addToList(TaskType.EVENT, "run");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
