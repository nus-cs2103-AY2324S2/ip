package actions;

import LeBron.LeBronException;
import task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the user interface of the application
 */
public class Ui {
    public static final String LINE = "____________________________________________________________\n";

    public static final String InputError = "Sorry, I'm not sure what you mean";

    public static String Intro() {
        return " Hello! I'm LeBron\n" +
                " What can I do for you?\n";
    }

    public void printWelcome() {
        System.out.println(Intro());
    }

    public static String Outro() {
        return " Bye. Hope to see you again soon!\n";
    }

    public void printOutro() {
        System.out.println(Outro());
    }
    

    public String printList(ArrayList<Task> tasks) throws LeBronException{
        try {
            if (tasks.size() == 0) {
                return "You have no tasks yet";
            } else {
                String result = "";
                for (int i = 0; i < tasks.size(); i++) {
                    int number = i + 1;
                    result += number + ". " + tasks.get(i) + "\n";
                }
                return "Here are the tasks in your list:\n" + result;
            }
        } catch (Exception e) {
            throw new LeBronException("Error loading tasks");
        }
    }

    public String printMarkMessage(Task task) {
        return LINE + "Nice! I've marked this task as done:" + "\n" + task.toString();
    }

    public String printUnmarkMessge(Task task) {
        return LINE + "OK, I've marked this task as not done yet-:" + "\n" + task.toString();
    }

    public String printAddMessage(Task task, int count) {
        if(count == 1){
            return  "Got it.I've added this task:\n"
                    + task.toString() + "\n"
                    +"Now you have " + count + " task in the list.\n";
        } else {
            return  "Got it.I've added this task:\n"
                    + task.toString() + "\n"
                    +"Now you have " + count + " tasks in the list.\n";
        }

    }

    public String printDeleteMessage(Task task, int count) {
        if (count == 1){
            return  "Noted. I've removed this task:\n"
                    + task.toString() + "\n"
                    + "Now you have " + count + " task in the list.\n";
        } else {
            return  "Noted. I've removed this task:\n"
                    + task.toString() + "\n"
                    + "Now you have " + count + " tasks in the list.\n";
        }
    }

    public String printError(String message) {
        return message;
    }

    public String printInputError(){
        return InputError;
    }

    public String printFindList(List<Task> findTasks) {
        String result = "";
        for (int i = 0; i < findTasks.size(); i++) {
            int num = i + 1;
            result += num + ". " + findTasks.get(i) + "\n";
        }
        return  "Here are some task you could be looking for:\n"
                + result;
    }

    public String printUpdate() {
        return "Okay! I have updated your task list.";
    }

    public String ReplyMessage() {
        return "";
    }


}
