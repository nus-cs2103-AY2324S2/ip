package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private static final String LONG_LINE = "____________________________________________________________";

    public void showLoadingError() {
        System.out.println(Ui.LONG_LINE);
        System.out.println("No saved tasks.");
        System.out.println(Ui.LONG_LINE);
    }

    public void showWelcome() {
        // Print logo
        String logo = " __   ___  _____   __       __       __    __      ___\n"
                + "|  | /  / |_   _| |  |     |  |     |  |  |  |    / _ \\\n"
                + "|  |/  /    | |   |  |     |  |     |  |  |  |   / /_\\ \\\n"
                + "|  |\\  \\   _| |_  |  |___  |  |___  |  |__|  |  / _____ \\\n"
                + "|__| \\__\\ |_____| |______| |______|  \\______/  /_/     \\_\\\n";
        System.out.println(logo);

        // Greet
        System.out.println(Ui.LONG_LINE);
        System.out.println("Killua online. What's my next quest?");
        System.out.println(Ui.LONG_LINE);
    }

    public String readCommand() {
        Scanner inputReader = new Scanner(System.in);
        return inputReader.nextLine();
    }

    public void showLine() {
        System.out.println(Ui.LONG_LINE);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void list(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getItems().size(); i++) {
            Task nextTask = tasks.getItems().get(i);
            System.out.println((i + 1) + ". " + nextTask.getDescriptionStatus());
        }
    }

    public void mark(Task task) {
        System.out.println(task.getMarkStatus());
        System.out.println(task.getDescriptionStatus());
    }

    public void add(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + tasks.getItems().size() + " tasks in the list.");
    }

    public void delete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescriptionStatus());
        System.out.println("Now you have " + (tasks.getItems().size() - 1) + " tasks in the list.");
    }

    public void exit() {
        // Exit
        System.out.println("Alright, I'm always one call away.");
    }


}
