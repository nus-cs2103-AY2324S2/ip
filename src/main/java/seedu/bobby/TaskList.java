package seedu.bobby;

import seedu.bobby.task.Task;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1> TaskList </h1>
 * This TaskList class is used to store all the Task class objects in an arraylist.
 * When Storage class loads the existing tasks from the file, the tasks are created and added to
 * TaskList.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() { //constructor
        tasks = new ArrayList<>();
    }

    /**
     * Adds the Task object to the arraylist and calls the function in Storage to store
     * the information of the newly added task in the file. Prints out the details of the
     * newly added task to be displayed to the user.
     *
     * @param task Task object to be added to the arraylist.
     * @param isNew boolean that indicates whether the task is an existing one loaded from
     *              the file or a newly added one.
     */
    public String addItem(Task task, boolean isNew) { //to append items to tasks
        tasks.add(task);
        if (isNew) {
            String result = "Got it. I've added this task:\n" + task.printFullDesc();
            result += String.format("Now you have %d %s in the tasks.\n", tasks.size(),
                        (tasks.size() == 1 ? "task" : "tasks"));
            try {
                Storage.add(task);
                return result;
            } catch (BobbyException e) {
                return e.getMessage();
            }
        } else {
            return "";
        }
    }

    /**
     * Removes the Task object at the index of num from the arraylist and also calls the function in
     * Storage to delete the information of the task stored in the file.
     *
     * @param num index of task to be deleted from the task list
     */
    public String delete(int num) throws BobbyException {
        assert num >= 0 : "task number should be a valid array index (>=0)";
        if (num - 1 >= tasks.size()) {
            throw new BobbyException("Oops, there isn't that task number in your list. Run list to check again.");
        }
        Task taskToDelete = tasks.get(num - 1);
        String result = "Got it. I've removed this task:\n" + taskToDelete.printFullDesc();
        tasks.remove(num - 1);
        result += String.format("Now you have %d %s in the task list.\n", tasks.size(),
                    (tasks.size() == 1 ? "task" : "tasks"));
        Storage.delete(num - 1);
        return result;
    }

    public String update(int num, String attribute, String toUpdate) throws BobbyException, IOException {
        Task taskToUpdate = tasks.get(num - 1);
        taskToUpdate.update(attribute, toUpdate);
        String result = "Got it. I've updated the task to this:\n" + taskToUpdate.printFullDesc();
        Storage.updateFile(num - 1, taskToUpdate.toStore());
        return result;
    }

    /**
     * Calls the mark function of the Task object and also calls the function in
     * Storage to update the information stored in the file.
     *
     * @param num index of task to be marked done.
     */
    public String mark(int num) throws BobbyException, IOException {
        assert num >= 0 : "task number should be a valid array index (>=0)";
        if (num - 1 >= tasks.size()) {
            throw new BobbyException("Oops, there isn't that task number in your list. Run list to check again.");
        }
        Task toMark = tasks.get(num - 1);
        String result = toMark.markDone(true);
        String toReplace = toMark.toStore();
        Storage.updateFile(num - 1, toReplace);
        return result;
    }

    /**
     * Calls the unmark function of the Task object and also calls the function in
     * Storage to update the information stored in the file.
     *
     * @param num index of task to be marked undone.
     */
    public String unmark(int num) throws BobbyException, IOException {
        assert num >= 0 : "task number should be a valid array index (>=0)";
        if (num - 1 >= tasks.size()) {
            throw new BobbyException("Oops, there isn't that task number in your list. Run list to check again.");
        }
        Task toUnmark = tasks.get(num - 1);
        String result = toUnmark.unmark();
        String toReplace = toUnmark.toStore();
        Storage.updateFile(num - 1, toReplace);
        return result;
    }

    /**
     * Returns the Task object at the index position of the task list.
     *
     * @param index the index of the task in the task list.
     * @return Task object that is in the position of the index in the arraylist.
     */
    public Task getTask(int index) {
        assert index >= 0 : "task number should be a valid array index (>=0)";
        return tasks.get(index);
    }

    /**
     * Prints information of all the lists in the task list by calling their individual functions
     * from the Task class or its subclasses Deadlines and Events to print individual task descriptions.
     * Prints out that there is 0 tasks in the task list if the arraylist of tasks is empty.
     */
    public String printList() {
        if (tasks.size() == 0) {
            return "Currently you have 0 tasks in your task list!\n";
        } else {
            String result = "";
            for (int i = 0; i < tasks.size(); i++) {
                result += tasks.get(i).printTaskDesc(i + 1);
            }
            return result;
        }
    }
}
