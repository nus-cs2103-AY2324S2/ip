package yapchit;

import yapchit.tasks.Task;

import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    public String scanInput() {
        String input = scanner.nextLine();
        return input;
    }
    public void printIntro() {
        String intro = "\t--------------------------------------------------\n"
                + "\tHello! I'm Yapchit\n"
                + "\tWhat can I do for you?\n"
                + "\t--------------------------------------------------";
        print(intro);
    }
    public void printOutro() {
        String outro = "\t--------------------------------------------------\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t--------------------------------------------------";
        print(outro);
    }
    public void printTasklistLoadError() {
        print("Unable to load existing list from file.\nThis could be due to corrupted file data or missing file.");
    }
    public void printTaskAdd(Task t, int size) {
        Ui.printLine();
        print("\tGot it. I've added this task:");
        print("\t\t"+ t.toString());
        String temp = size == 1 ? "task" : "tasks";
        print("\tNow you have " + size +" " + temp + " in the list");
        Ui.printLine();
    }

    public void printTaskDelete(Task t, int size) {
        Ui.printLine();
        Ui.print("\tNoted. I've removed this task:");
        Ui.print("\t\t" + t.toString());
        String temp = size == 1 ? "task" : "tasks";
        Ui.print("\tNow you have " + size +" " + temp + " in the list");
        Ui.printLine();
    }

    public void printTaskMark(Task t, boolean isDone) {
        Ui.printLine();

        if (isDone) {
            Ui.print("\t" + "Nice! I've marked this task as done:");
            Ui.print("\t\t" + t.toString());
        } else {
            Ui.print("\t" + "OK, I've marked this task as not done yet:");
            Ui.print("\t\t" + t.toString());
        }

        Ui.printLine();
    }

    public void printList(TaskList tasks) {
        Ui.printLine();
        Ui.print("\t" + "Here are the tasks in your list:");

        for (int i = 0; i < tasks.getListSize(); i++) {
            int idx = i + 1;
            Task item = tasks.getItem(i);
            Ui.print("\t" + idx + "." + item.toString());
        }

        Ui.printLine();
    }
    public static void printLine() {
        print("\t--------------------------------------------------");
    }
    public static void print(Object o) {
        System.out.println(o);
    }
}
