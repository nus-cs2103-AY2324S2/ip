package fishstock;

import fishstock.FishStock.Keyword;

import java.util.ArrayList;

/**
 * Encapsulates a TaskList object.
 * Handles all functions related to the array storing Tasks.
 */
class TaskList {
    protected final ArrayList<Task> list;

    protected TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    protected TaskList() {
        this.list = new ArrayList<>();
    }

    protected int getSize() {
        return list.size();
    }

    /**
     * Prints all Tasks in array.
     */
    protected void printTasks() {
        for (int i = 0; i < list.size(); i++) {
            Ui.printMsg((i + 1) + "." + list.get(i));
        }
    }

    /**
     * Marks whether Task is done.
     * @param keyword The keyword command.
     * @param input The input from user.
     * @return The marked/unmarked Task.
     * @throws FishStockException The exceptions while changing the mark.
     */
    protected Task changeMark(Keyword keyword, String input) throws FishStockException {
        Integer idx = Parser.getTaskFromIndex(input);
        try {
            Task task = list.get(idx);
            if (keyword == Keyword.MARK) {
                task.markAsDone();
            } else if (keyword == Keyword.UNMARK) {
                task.markAsUndone();
            }
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    /**
     * Removes Task from array.
     * @param input The input from user.
     * @return The removed Task.
     * @throws FishStockException The exceptions while removing the Task.
     */
    protected Task deleteTask(String input) throws FishStockException {
        Integer idx = Parser.getTaskFromIndex(input);
        try {
            Task task = list.get(idx);
            list.remove(task);
            return task;

        } catch (IndexOutOfBoundsException e) {
            throw new FishStockException("OH NOSE! Task number must be in valid range..");
        }
    }

    /**
     * Adds Task into array.
     * @param keyword The keyword command.
     * @param input The input from user.
     * @return The added Task.
     * @throws FishStockException The exceptions while adding the Task.
     */
    protected Task addTask(Keyword keyword, String input) throws FishStockException {
        Task task = null;
        switch (keyword) {
        case TODO:
            task = Todo.of(input);
            break;
        case DEADLINE:
            task = Deadline.of(input);
            break;
        case EVENT:
            task = Event.of(input);
            break;
        }
        list.add(task);
        return task;
    }
}
