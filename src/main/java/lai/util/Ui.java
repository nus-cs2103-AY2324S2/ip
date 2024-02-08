package lai.util;

import lai.task.Task;

import java.util.Scanner;

public class Ui {
    protected Scanner scanner;

    public Ui (Scanner scanner) {
        this.scanner = scanner;
    }

    public static void printStart() {
        System.out.println("Hi there, I am Lai. Your friendly fairly useless chatbot.");
        System.out.println("What can I assist you with today?");
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Goodbye, we shall meet again. Hopefully.");
        printLine();
    }

    public static void printLine() {
        System.out.println("---------------------------------------------------------");
    }

    public static void printTaskMarked(Task t) {
        printLine();
        System.out.println("You actually did something? Marked done:");
        System.out.println(t);
        printLine();
    }
    public static void printTaskUnmarked(Task t) {
        printLine();
        System.out.println("Come on now, don't be useless. Marked not done:");
        System.out.println(t);
        printLine();
    }

    public static void printTaskAdded(Task newTask, TaskList tasks) {
        printLine();
        System.out.println("Added: " + newTask);
        System.out.println(String.format("Total number of tasks: %s", tasks.size()));
        printLine();
    }

    public static void printTaskDeleted(Task t) {
        printLine();
        System.out.println("I have deleted:");
        System.out.println(t);
        printLine();
    }

    public static void listTasks(TaskList tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
        }
        printLine();
    }

    public static void printLaiException(LaiException e) {
        printLine();
        System.out.println(e);
        printLine();
    }

    public static void printNumberFormatException(NumberFormatException e) {
        printLine();
        System.out.println("Error occurred: Numbers only, please.");
        printLine();
    }

    public String[] getInput() {
        System.out.print("> ");
        return Parser.parse(scanner);
    }
}
