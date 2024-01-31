package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int deleteId, Ui ui) {
        ui.deleteMessage(taskList.get(deleteId));
        taskList.remove(deleteId);
    }

    public void unmarkTask(int unmarkId, Ui ui) {
        taskList.get(unmarkId).markNotDone();
        ui.unmarkMessage(taskList.get(unmarkId));
    }

    public void markTask(int markId, Ui ui) {
        taskList.get(markId).markDone();
        ui.markMessage(taskList.get(markId));
    }

    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void listTask(Ui ui) {
        if (taskList.isEmpty()) {
            ui.setIndentedLine();
            System.out.println("  Sorry, Tasklist is empty.");
            ui.setIndentedLine();
        } else {
            ui.setIndentedLine();
            System.out.println("  " + "Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println("  " + (i + 1) + "." + currTask.toString());
            }
            ui.setIndentedLine();
        }
    }

    /**
     * Method to find tasks that match the search.
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
     * Method to list out the tasks that match the search.
     *
     * @param matchedTasks List that contains matched tasks.
     * @param ui Ui to set indented line.
     */
    public void listMatchedTasks(ArrayList<Task> matchedTasks, Ui ui) {
        if (matchedTasks.isEmpty()) {
            ui.setIndentedLine();
            System.out.println("  Sorry, there are no tasks that match your search...");
            ui.setIndentedLine();
        } else {
            ui.setIndentedLine();
            System.out.println("  " + "Here are the tasks that match your search:");
            for(int i = 0; i < matchedTasks.size(); i++) {
                Task currTask = matchedTasks.get(i);
                System.out.println("  " + (i + 1) + "." + currTask.toString());
            }
            ui.setIndentedLine();
        }
    }
}
