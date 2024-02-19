package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for new TaskList.
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param taskList Existing arrayList.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds task.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes task.
     *
     * @param deleteId Id of task to be deleted.
     * @param ui Ui to generate deleteMessage.
     */
    public String deleteTask(int deleteId, Ui ui) {
        String temp = ui.deleteMessage(taskList.get(deleteId));
        taskList.remove(deleteId);
        return temp;
    }

    /**
     * Unmarks task.
     *
     * @param unmarkId Id of task to be unmarked.
     * @param ui Ui to generate unmarkMessage.
     */
    public String unmarkTask(int unmarkId, Ui ui) {
        taskList.get(unmarkId).markNotDone();
        return ui.unmarkMessage(taskList.get(unmarkId));
    }

    /**
     * Marks task.
     *
     * @param markId Id of task to be marked.
     * @param ui Ui to generate markMessage.
     */
    public String markTask(int markId, Ui ui) {
        taskList.get(markId).markDone();
        return ui.markMessage(taskList.get(markId));
    }

    public String tagTask(String tag, int tagId, Ui ui) {
        taskList.get(tagId).replaceTag(tag);
        return ui.tagMessage(taskList.get(tagId));
    }

    /**
     * Gets tasks from taskList.
     *
     * @return taskList.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Gets size of taskList.
     *
     * @return size of taskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Prints list of tasks.
     *
     */
    public String listTask() {
        String output = "  Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
              output += "\n  " + (i + 1) + ". " +
                    taskList.get(i).toString();
        }
        return output;
    }

    /**
     * Finds tasks that match the search.
     *
     * @param match The keyword to be used.
     * @return Arraylist of matched tasks.
     */
    public ArrayList<Task> find(String match) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(match)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Lists out the tasks that match the search.
     *
     * @param matchedTasks List that contains matched tasks.
     */
    public String listMatchedTasks(ArrayList<Task> matchedTasks) {
        StringBuilder output = new StringBuilder();
        if (matchedTasks.isEmpty()) {
            return "  Sorry, there are no tasks that match your search...";
        } else {
            output.append("  " + "Here are the tasks that match your search:").append("\n");
            for(int i = 0; i < matchedTasks.size(); i++) {
                Task currTask = matchedTasks.get(i);
                output.append("  ").append(i + 1).append(". ")
                        .append(currTask.toString()).append("\n");
            }
            return output.toString();
        }
    }
}
