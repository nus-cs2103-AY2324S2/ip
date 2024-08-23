package xilef;

import xilef.task.Task;
import xilef.task.TaskList;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void showLoadingError() {
    }

    public static String showWelcome() {
        return "\tHello, my name is Xilef.\n\tHow may I help you today??\n";
    }

    public String showError(XilefException e) {
        return "\t" + e.getMessage();
    }

    public String showExit() {
        return "\tBye bye, see you next time!!!";
    }

    public String showAdded(Task task) {
        String s = "\tAdded a new task to the list!\n\t  " + task.toString();
        return s;
    }

    public String showMarked(Task task) {
        return "\tGreat job, you have accomplished this task:\n\t  " + task.toString() + "\n";
    }

    public String showUnmarked(Task task) {
        return "\tReminder, you have not completed this task yet:\n\t  " + task.toString() + "\n";
    }

    public String showDeleted(Task task) {
        return "\tRemoved the following task:\n\t  " + task.toString();
    }

    public String showSize(TaskList tasks) {
        if (tasks.size() <= 1) {
            return "\tYou now have " + tasks.size() + " task remaining";
        } else {
            return "\tYou now have " + tasks.size() + " tasks remaining";
        }
    }

    public String showTasksStatus(TaskList tasks) {
        if (tasks.size() <= 1) {
            return "\tYou have " + tasks.size() + " too many task to do!!!" +
                    "\n\tQuickly start working on them!!!\n";
        } else {
            return "\tYou have " + tasks.size() + " too many tasks to do!!!" +
                    "\n\tQuickly start working on them!!!\n";
        }
    }

    public String showUndo() {
        return "\tYour previous action has been undone";
    }

    public String showFind(TaskList tasks) {
        if (tasks.size() == 0) {
            return "\tThere are no matching tasks in your list";
        } else {
            StringBuilder s = new StringBuilder("\tThese are the matching tasks in your list");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task t = tasks.get(i - 1);
                s.append("\n\t").append(i).append(".").append(t.toString());
            }
            return s.toString();
        }
    }

    public String showList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "\tThere is nothing on your agenda";
        } else {
            StringBuilder s = new StringBuilder("\tThese are the things on your agenda today");
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task t = tasks.get(i - 1);
                s.append("\n\t").append(i).append(".").append(t.toString());
            }
            return s + "\n";
        }
    }
}
