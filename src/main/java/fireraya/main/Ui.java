package fireraya.main;

import fireraya.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc;

    Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static String startMessage() {
        String a = print("--------------------------------\n" );
        String b = print("Hello, my name is RONNIE!\n");
        String c = print("What can I do for you today sir?\n");
        String d = print("--------------------------------\n");
        String combined = a + b + c + d;
        return combined;
    }
    public static String print(String message) {
        return message;
    }
    public String displayLoadingError(String message) {
        return print("An error occurred at file load" + message);
    }

    public String showLine() {
        return print("----------------------------------------");
    }

    public String taskAddedMsg(Task t, int size) {
        String a = print("I've added this task:\n");
        String b = t.toString() + "\n";
        String c = print("You have " + size + " tasks currently.\n");
        String combined = a + b + c;
        return combined;

    }

    public String listTasks(ArrayList<Task> tasks) {
        String a = print("Here is a list of your tasks!\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            int num = i + 1;
            a += print(num + "." + current.toString() + "\n");
        }
        return a;
    }

    public String listTaskAt(ArrayList<Task> tasks, int i) {
        Task current = tasks.get(i);
        int num = i + 1;
        return print( num + "." + current.toString());
    }

    public String end() {
        return print("Bye, hope to see you soon!");
    }


}
