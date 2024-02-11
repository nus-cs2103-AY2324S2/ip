package task;

import exception.TobiasException;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     * */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to the TaskList.
     *
     * @param task Task to be added to the TaskList.
     * */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at index from the TaskList.
     *
     * @param index index of Task to be retrieved from TaskList.
     * */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at index from the TaskList.
     *
     * @param index index of Task to be removed from TaskList.
     * */
    public void removeFromList(int index) {
        tasks.remove(index);
    }


    /**
     * Formats all the tasks in the TaskList to a save .txt format.
     *
     * @return String format of all the tasks in the list in a save .txt friendly format.
     * */
    public String saveMechanism() {
        String result = "";

        for (Task task : tasks) {
            result += task.storagePrinter() + System.lineSeparator();
        }

        return result;
    }


    /**
     * Number of tasks in the TaskList.
     *
     * @return number of tasks in the TaskList.
     * */
    public int taskNum() {
        return tasks.size();
    }


    /**
     * Prints all tasks in the TaskList in console.
     *
     * @throws TobiasException if the TaskList is empty.
     * */
    public String printList() throws TobiasException {
        StringBuilder result = new StringBuilder(Ui.printDivider()
                + System.lineSeparator()
                + "Your list is empty at the moment !"
        );

        try {
            if (tasks.isEmpty()) {
                throw new TobiasException("Your list is empty at the moment !");
            } else {
                result = new StringBuilder(Ui.printDivider()
                        + System.lineSeparator()
                        + "Here are the tasks in your list: "
                );
                for (Task task : tasks) {
                    int index = tasks.indexOf(task);
                    result.append(System.lineSeparator()).append(task.taskPrinter(index));
                }
            }
        } catch (TobiasException e) {
            e.printMessage();
        }

        result.append(System.lineSeparator()).append(Ui.printDivider());

        return result.toString();
    }


    /**
     * Prints the tasks in the TaskList that have the keyword in console.
     *
     * @param keyword The keyword that needs to be in the task to be printed.
     * @throws TobiasException if there are no tasks with the given keyword.
     * */
    public String printListFind(String keyword) throws TobiasException {
        StringBuilder result;

        List<Task> foundResults = new ArrayList<>();

        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                foundResults.add(task);
            }
        }

        try {
            if (foundResults.isEmpty()) {
                throw new TobiasException("Could not find any results containing keyword : " + keyword);
            } else {
                result = new StringBuilder(Ui.printDivider()
                        + System.lineSeparator()
                        + "Here are the matching tasks in your list: "
                );
                for (Task task : foundResults) {
                    int index = tasks.indexOf(task);
                    result.append(System.lineSeparator()).append(task.taskPrinter(index));
                }
            }
        } catch (TobiasException e) {
            result = new StringBuilder(Ui.printDivider()
                    + System.lineSeparator()
                    + e.printMessage());
        }

        result.append(System.lineSeparator()).append(Ui.printDivider());

        return result.toString();
    }

    public void replaceTask(int index, Task task) {
        tasks.set(index, task);
    }
}
