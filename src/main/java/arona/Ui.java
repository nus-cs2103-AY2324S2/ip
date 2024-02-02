package arona;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private String logo =        "    _____ \n" +
            /* */                "   /  _  \\_______   ____   _____ _____ \n" +
            /*   */              "  /  /_\\  \\_  __  \\/  _ \\ /     \\___  \\ \n" +
            /*   */              " /    |    \\  | \\_ ( <_> )   |   \\/ __ \\_ \n" +
            /* */                " \\____|__  /__|    \\____/|___|_  (____  / \n" +
            /* */                "         \\/                    \\/     \\/ ";
    private String greetings = "こんにちは先生、私はアロナです. \n"
            + "どういうご用件ですか?　\n"
            + "ここで先生のスケジュールが決まります.";
    private String goodbye = "Goodbye sensei! Hope to see you soon!";
    public void greetings() {
        System.out.println("開始中... \n" + logo);
        System.out.println(greetings);
    }

    public void quitApplication() {
        System.out.println(goodbye);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Sensei! Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    public void taskAdded(ArrayList<Task> tasks) {
        System.out.println("Arona has added this task to sensei's task list!: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Arona has counted " + tasks.size() + " tasks in the list!");
    }

    public void taskDeleted(Task task, int size) {
        System.out.println("Arona has removed this task!: ");
        System.out.println(task.toString());
        System.out.println("Arona has counted " + size + " tasks in the list!");
    }

    public void alreadyMarked(boolean status) {
        System.out.println("Sensei, the task has already been marked as " + (status ? "done!" : "not done!"));
    }

    public void markTask() {
        System.out.println("Congratulation, sensei! arona.Arona has marked the task as done!:");
    }

    public void unmarkTask() {
        System.out.println("I understand, sensei! arona.Arona has marked the task as not done!:");
    }

    public String showLine() {
        return "---------------------------------";
    }
}
