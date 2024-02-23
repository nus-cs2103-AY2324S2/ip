package Dude;

import java.util.ArrayList;
import java.util.Scanner;

class Ui {

    public Ui() {
    }

    public String showWelcome() {
        return "Hello! I'm Dude\nWhat can I do for you?";
    }

    public String showAddTask(Event event) {
        return "Added Event: " + event;
    }

    public String showAddTask(Deadline deadline) {
        return "Added Deadline: " + deadline;
    }

    public String showAddTask(ToDo todo) {
        return "Added Todo: " + todo;
    }

    public String showDelete(Task task) {
        return "Deleted: " + task;
    }

    public String showDone(Task task) {
        return "Marked as Done: " + task;
    }

    public String showUndone(Task task) {
        return "Marked as Not Done: " + task;
    }

    public String showGoodbye() {
        return ("Bye. Hope to see you again soon!");
    }

    public String showError(String message) {
        return ("Error: " + message);
    }

    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();

        if (tasks.isEmpty()) {
            return ("No Tasks to show.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            s.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }
}
