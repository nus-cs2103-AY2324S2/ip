package fireraya.main;

import fireraya.task.Task;

import java.io.IOException;
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

    public void startMessage() {
        print("--------------------------------");
        print("Hello, my name is Fireraya!");
        print("What can I do for you today sir?");
        print("--------------------------------");
    }
    public void print(String message) {
        System.out.println(message);
    }
    public void displayLoadingError(String message) {
        print("An error occurred at file load" + message);
    }

    public void showLine() {
        print("----------------------------------------");
    }

    public void taskAddedMsg(Task t, int size) {
        System.out.println("I've added this task:");
        System.out.println(t);
        System.out.println("You have " + size + " tasks currently.");
    }

    public void listTasks(ArrayList<Task> tasks) {
        print("Here is a list of your tasks!");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            int num = i + 1;
            print(num + "." + current.toString());
        }
    }

    public void listTaskAt(ArrayList<Task> tasks, int i) {
        Task current = tasks.get(i);
        int num = i + 1;
        print( num + "." + current.toString());
    }

    public void end() {
        print("Bye, hope to see you soon!");
    }


}
