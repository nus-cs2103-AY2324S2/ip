package duke.task;

import duke.exceptions.IllegalParamException;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class TaskList implements Iterable<Task>{
    private List<Task> taskList = new ArrayList<>();

    @Override
    public String toString() {
        String out = "Here is the list of things I remember!\n";
//        int count = 1;

        for (Task currentItem : this.taskList) {
            out += this.taskList.indexOf(currentItem) + 1 + "." + currentItem + "\n";
        }
        return out.equals("Here is the list of things I remember!\n") ? "Looks like you have nothing to do! Yay!\n" : out;
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }

    public void add(Task taskName) {
        this.taskList.add(taskName);
    }

    public Task getTask(int index) throws IllegalParamException {
        try {
            return this.taskList.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParamException("I cant do that! The task does not exist!");
        }

    }

    public void deleteTask(int index) throws IllegalParamException {
        try {
            this.taskList.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalParamException("I cant delete that task! It does not exist!");
        }
    }

    /**
     * Returns count of number of tasks in list
     *
     * @return int value
     */
    public int countTasks() {
        return taskList.size();
    }

    /**
     * Finds tasks with searchWord in their description.
     *
     * @param searchWord
     * @return
     */
    public String find(String searchWord) {
        StringBuilder out = new StringBuilder();
        int count = 0;

        for (Task currentItem : this.taskList) {
            String taskString = currentItem.toString().toLowerCase();

            if (taskString.contains(searchWord)) {
                count++;
                out.append("\n").append(this.taskList.indexOf(currentItem) + 1).append(".").append(currentItem);
            }
        }
        out.insert(0, "I found " + count + " of them!");
        out.insert(0, "Here are the matching tasks! ");

        return out.toString();
    }
}
