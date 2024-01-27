package duke;

public class Ui {
    public void welcome() {
        System.out.println("Hello! I'm GuanGuanBot!");
        System.out.println("What can I do for you?");
        line();
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void addTask() {
        System.out.println("Got it. I've added this task:");
    }

    public void deleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

    public void countTasks(int i) {
        System.out.printf("Now you have %s tasks in the list.%n", i);
    }

    public void tasks(TaskList items) {
        if (items.isEmpty()) {
            System.out.println("You have no tasking available.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%s. %s%n", i + 1, items.get(i));
            }
        }
    }

    public void markTask() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void unmarkTask() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    public void line() {
        System.out.println("____________________________________________________________");
    }

    public void emptyLine() {
        System.out.println();
    }

    public void error(String message) {
        System.out.printf("[!] %s\n", message);
    }
}
