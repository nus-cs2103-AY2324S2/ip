package duke;

import java.util.ArrayList;

public class TaskList {
    static String horzLine = "____________________________________________________________";
    static void printWithLines(String message) {
        System.out.println(horzLine);
        System.out.println(message);
        System.out.println(horzLine);
    }
    private ArrayList<Task> taskList = new ArrayList<>(100);

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
        printWithLines("Got it. I've added this task:\n   " + newTask.toString() +
                "\nNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks ": " task ") +
                "in the list.");
    }

    public void delete(int index) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("Nothing is in the list yet");
        }
        if (index < 1 || index > taskList.size()) {
            throw new DukeException("Please enter a number between 1 and " + taskList.size());
        }
        Task tempTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        printWithLines("Noted. I've removed this task:\n   " + tempTask.toString() +
                "\nNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks ": " task ") +
                "in the list.");
    }
}
