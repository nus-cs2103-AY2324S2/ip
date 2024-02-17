package seedu.chatteroo.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listStore = new ArrayList<>();

    public TaskList(ArrayList<Task> listStore) {
        this.listStore = listStore;
    }

    // function to mark task as done and print it
    public void markTaskAsDone(int taskNum) {
        listStore.get(taskNum - 1).markAsDone();
    }

    //function to mark task as not done and print it
    public void markTaskAsNotDone(int taskNum) {
        listStore.get(taskNum - 1).markAsNotDone();
    }

    //function to delete task and move remaining tasks up in the list
    public void deleteTask(int taskNum) {
        listStore.remove(taskNum - 1);
    }

    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : listStore) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    public String getTaskListString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listStore.size(); i++) {
            int taskNumber = i + 1;
            sb.append(taskNumber)
              .append(". ")
              .append(listStore.get(i).toString())
              .append("\n");
        }
        return sb.toString();
    }

    public int getTaskListSize() {
        return listStore.size();
    }

    public Task getTask(int index) {
        return listStore.get(index - 1);
    }

    public void addTask(Task task) {
        listStore.add(task);
    }

}
