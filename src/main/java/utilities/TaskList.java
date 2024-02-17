package utilities;

import java.time.LocalDate;
import java.util.ArrayList;

import datesandtimes.DateTimeParser;
import exceptions.RyanGoslingBadFormatException;
import exceptions.RyanGoslingException;
import tasks.Task;

/**
 * The TaskList class represents a list of tasks and provides methods for manipulating and interacting with the list.
 */
public class TaskList {

    /**
     * The ArrayList containing the tasks in the task list.
     */
    private final ArrayList<Task> listOfTasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskList The initial list of tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The task to be added to the list.
     * @return A formatted string containing the command printout.
     */
    public String add(Task newTask) {
        this.listOfTasks.add(newTask);
        return ResponseHandler.commandPrint(newTask, this.listOfTasks.size());
    }

    /**
     * Prints the entire task list.
     *
     * @return A formatted string containing the list of tasks.
     */
    public String printList() {
        return ResponseHandler.commandListPrint(this.listOfTasks);
    }

    /**
     * Changes the status (done or not done) of a task in the list.
     *
     * @param commandSplit The list containing the supposed action and index.
     * @return A formatted string containing the status change printout.
     */
    public String changeStatusOfItem(String[] commandSplit) throws RyanGoslingException {
        //String[] commandSplit = taskInputByUser.split(" ");
        if (commandSplit.length != 2) {
            throw new RyanGoslingBadFormatException("Wrong format! (un)mark <number>");
        }
        try {
            String taskAction = commandSplit[0];
            assert taskAction.equals("mark") || taskAction.equals("unmark") : "Action should be either mark or unmark!";

            int indexOfTask = Integer.parseInt(commandSplit[1]) - 1;
            return this.listOfTasks.get(indexOfTask).changeStatus(taskAction);
        } catch (IndexOutOfBoundsException e) {
            throw new RyanGoslingBadFormatException("Index out of bounds! Not so cash money of you");
        }
    }

    /**
     * Removes a task from the list by its index.
     *
     * @param commandSplit The index of the task to be removed.
     * @return A formatted string containing the remove printout.
     */
    public String removeIndex(String[] commandSplit) throws RyanGoslingException {
        if (commandSplit.length != 2) {
            throw new RyanGoslingBadFormatException("Invalid number arguments, type delete <index>");
        }
        assert commandSplit[0].equals("delete") : "Task should be delete here!";
        try {
            int indexOfTask = Integer.parseInt(commandSplit[1]) - 1;
            Task taskToBeRemoved = listOfTasks.get(indexOfTask);
            this.listOfTasks.remove(indexOfTask);
            return ResponseHandler.removePrinter(taskToBeRemoved, listOfTasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new RyanGoslingBadFormatException("Index should be a valid index that is in range of list!");
        } catch (NumberFormatException e) {
            throw new RyanGoslingBadFormatException("Index is not a number!");
        } catch (Exception e) {
            throw new RyanGoslingException(e.getMessage());
        }
    }

    /**
     * Writes the current task list to the hard drive using the provided Storage object.
     *
     * @param storage The Storage object used for writing to the hard drive.
     */
    public void writeToFile(Storage storage) {
        storage.writeToTaskList(this.listOfTasks);
    }

    /**
     * Finds tasks in the list that match the specified pattern.
     *
     * @param taskPattern The pattern to be matched against task names.
     * @return A formatted string containing the found tasks printout.
     */
    public String findTasks(String taskPattern) {
        ArrayList<Task> tasksWithPattern = new ArrayList<>();
        for (Task task : this.listOfTasks) {
            String currTaskName = task.getTaskName();
            if (currTaskName.contains(taskPattern)) {
                tasksWithPattern.add(task);
            }
        }
        return ResponseHandler.printFoundTasks(tasksWithPattern);
    }

    /**
     * Updates the name of a task in the list and writes to the hard drive.
     *
     * @param index        The index of the task to be updated.
     * @param newTaskName  The new name for the task.
     * @param taskStorage  The Storage object used for writing to the hard drive.
     */
    public void updateNameOfTask(int index, String newTaskName, Storage taskStorage) {
        this.listOfTasks.get(index).updateTaskName(newTaskName);
        this.writeToFile(taskStorage);
    }

    /**
     * Gets the type of task in the list.
     *
     * @param index The index of the task.
     * @return The type of the task.
     */
    public String getTaskType(int index) {
        return this.listOfTasks.get(index).getTaskType();
    }

    /**
     * Updates the dates of a task in the list and writes to the hard drive.
     *
     * @param index        The index of the task to be updated.
     * @param listOfDates  An array containing the start and end dates for the task.
     * @param taskStorage  The Storage object used for writing to the hard drive.
     */
    public void updateDatesOfTask(int index, String[] listOfDates, Storage taskStorage) {
        String firstDate = listOfDates[0];
        String secondDate = listOfDates[1];
        String[] originalDateList = this.listOfTasks.get(index).getDates();
        if (!firstDate.equals("NA")) {
            LocalDate.parse(firstDate);
            listOfDates[0] = firstDate;
        } else {
            listOfDates[0] = originalDateList[0];
        }
        if (!secondDate.equals("NA")) {
            LocalDate.parse(secondDate);
            listOfDates[1] = secondDate;
        } else {
            listOfDates[1] = originalDateList[1];
        }
        this.listOfTasks.get(index).setDate(listOfDates);
        this.writeToFile(taskStorage);
    }

    /**
     * Updates the times of a task in the list and writes to the hard drive.
     *
     * @param index        The index of the task to be updated.
     * @param listOfTimes  An array containing the start and end times for the task.
     * @param taskStorage  The Storage object used for writing to the hard drive.
     */
    public void updateTimesOfTask(int index, String[] listOfTimes, Storage taskStorage) {
        String firstTime = listOfTimes[0];
        String secondTime = listOfTimes[1];
        String[] originalTimeList = this.listOfTasks.get(index).getTimes();
        if (!firstTime.equals("NA")) {
            DateTimeParser.parseTimeAsLocalTime(firstTime);
            listOfTimes[0] = firstTime;
        } else {
            listOfTimes[0] = originalTimeList[0];
        }
        if (!secondTime.equals("NA")) {
            DateTimeParser.parseTimeAsLocalTime(secondTime);
            listOfTimes[1] = secondTime;
        } else {
            listOfTimes[1] = originalTimeList[1];
        }
        this.listOfTasks.get(index).setTime(listOfTimes);
        this.writeToFile(taskStorage);
    }

    /**
     * Validates if the given index is within the valid range.
     *
     * @param index The index to be validated.
     * @return {@code true} if the index is valid, {@code false} otherwise.
     */
    public boolean validateIndex(int index) {
        return index >= 0 && index < listOfTasks.size();
    }

    /**
     * Overrides the toString method to provide information about the number of tasks in the list.
     *
     * @return A string representation of the number of tasks in the list.
     */
    @Override
    public String toString() {
        return "Now you have " + listOfTasks.size() + " tasks in the list.";
    }
}

