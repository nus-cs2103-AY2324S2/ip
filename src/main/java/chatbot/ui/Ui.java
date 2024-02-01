package chatbot.ui;

import java.util.ArrayList;
import chatbot.task.Task;

public class Ui {

    public static void greet(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("What's up chief? " + name + " here!\n");
        sb.append("What can I do for you?");
        Ui.output(sb.toString());
    }

    public static void bye() {
        Ui.output("Bye. Hope to see you again soon!");
    }

    public static String addTaskSuccess(Task task, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append("  " + task.toString() + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static String markTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  " + task.toString());
        return sb.toString();
    }

    public static String unmarkTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append("  " + task.toString());
        return sb.toString();
    }

    public static String listTasks(ArrayList<Task> tasks, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < numTasks; i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public static String listFindTasks(ArrayList<Task> tasks, int numTasks) {
        StringBuilder sb = new StringBuilder();
        String task = numTasks == 1 ? "task" : "tasks";
        String article = numTasks == 1 ? "is" : "are";
        sb.append("There " + article + " " + numTasks + " matching " + task + " in your list:\n");
        for (int i = 0; i < numTasks; i++) {
            sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return sb.toString();
    }
    
    public static String deleteTask(Task task, int numTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append("  " + task.toString() + "\n");
        sb.append("Now you have " + numTasks + " tasks in the list.");
        return sb.toString();
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void drawLine() {
        indent();
        System.out.println("____________________________________________________________________________");
    }

    public static void output(String sentences) {
        drawLine();
        String[] arr = sentences.split("\n");
        for (String sentence : arr) {
            indent();
            System.out.println(sentence);
        }
        drawLine();
    }

}