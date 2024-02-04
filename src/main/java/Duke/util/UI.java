package Duke.util;

import java.util.Scanner;
import java.util.ArrayList;
import Duke.tasks.Task;
import Duke.exceptions.DukeException;

public class UI {
    private static final String hRULER = "____________________________________________________________\n";
    private static final String[] token = new String[]{
            "____________________________________________________________",
            "Hello! I'm chinesepoliceman",
            "What can I do for you?",
            "____________________________________________________________",
            " Bye. Hope to see you again soon!",
            "____________________________________________________________"
    };
    private Scanner sc = new Scanner(System.in);
    public String receiveNextLine() {
        System.out.print("Command: ");
        return this.sc.nextLine();
    }
    public void displayMark(Task t) {
        System.out.printf("%s Nice! I've marked this task as done:\n   %s\n%s",
                hRULER, t.toString(), hRULER);
    }
    public void displayUnMark(Task t) {
        System.out.printf("%s OK, I've marked this task as not done yet:\n   %s\n%s",
                hRULER, t.toString(), hRULER);
    }
    public void displayDelete(Task t, int currentIdx) {
        System.out.printf("%s Noted. I've removed this task:\n   %s\n Now you have %d tasks in the list.\n%s",
                hRULER, t, currentIdx, hRULER);
    }
    public void displayAdd(Task t, int currentIdx) {
        System.out.printf("%s Got it. I've added this task:\n  " +
                " %s\n Now you have %d tasks in the list.\n%s", hRULER, t, currentIdx, hRULER);
    }
    public void displayList(ArrayList<Task> l) {
        System.out.printf("%s Here are the tasks in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }
    public void displayIntro() {
        for (int i = 0; i < 4; i++) {
            System.out.println(token[i]);
        }
    }
    public void displayExit() {
        for (int i = 3; i < 6; i++) {
            System.out.println(token[i]);
        }
    }
    public void displayExceptionMsg(DukeException e) {
        System.out.printf("%s%s%s", hRULER, e, hRULER);
    }
    public void displayFoundList(ArrayList<Task> l) {
        System.out.printf("%s Here are the deadlines/events with specified date in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }
    public void displayFoundTask(ArrayList<Task> l) {
        System.out.printf("%s Here are the deadlines/events with specified word in your list:\n", hRULER);
        for (int i = 0; i < l.size(); i++) {
            System.out.printf(" %d.%s\n", i + 1, l.get(i).toString());
        }
        System.out.println(hRULER);
    }

}
