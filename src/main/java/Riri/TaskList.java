package Riri;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();
    public TaskList(ArrayList<String> contents) {
        for (String c : contents) {
            addTask(parser(c));
        }
    }
    public void returnList() {
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i-1).toString());
        }
        if (taskList.size() == 0) {
            System.out.println("You have no items in your list.");
        }
    }
    public void addTask(Task item) {
        taskList.add(item);
        System.out.println("Got it. Added: " + item.toString());
    }
    public void loadTask(Task item) {
        taskList.add(item);
    }
    public void mark(int i) {
        this.taskList.get(i-1).markDone();
        this.returnList();
    }
    public void unmark(int i) {
        this.taskList.get(i-1).markUndone();
        this.returnList();
    }
    /**
     * This function removes a task from the task list.
     * @param index remove index'th task
     */
    public void delete(int index) {
        this.taskList.remove(index);
        System.out.println("Deleted task no. " + index);
        System.out.println("You have " + this.len() + " tasks left");
    }
    public int len() {
        return taskList.size();
    }

    /**
     * Parses the string to create a Task object
     * @param line string that describes a task.
     * @return a task object
     *
     */
    private static Task parser(String line) {
        if (line.startsWith("[D]")) {
            return Deadline.parseDeadlineFromString(line);
        } else if (line.startsWith("[E]")) {
            return Event.parseEventFromString(line);
        } else if (line.startsWith("[T]")) {
            return Todo.parseTodoFromString(line);
        }
        return null;
    }
    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i < taskList.size()+1; i++) {
            s += (taskList.get(i-1).toString() + "\n");
        }
        return s;
    }

}