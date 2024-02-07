package duke.main;

import java.util.Scanner;

public class Ui {
    private TaskList taskList;
    private Scanner scanner;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! What tasks do you have?");
    }

    public void printOnMark(int index){
        System.out.println("Task marked as done. Good job!");
        System.out.println(taskList.get(index).toString());
    }

    public void printOnUnmark(int index){
        System.out.println("Alright! Task marked as not done");
        System.out.println(taskList.get(index).toString());
    }

    public void printOnAdd() {
        System.out.println("Added new task: ");
        System.out.println(taskList.get(taskList.getSize() - 1).toString());
        printTotal();
    }

    public void printOnDelete(int index) {
        System.out.println("Removing task from list");
        System.out.println(taskList.get(index).toString());
    }

    public void printList() {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ": " + taskList.get(i).toString());
        }
    }

    public void printTotal() {
        System.out.println("current number of tasks: " + taskList.getSize());
    }

    public void printExitMessage() {
        System.out.println("Goodbye!");
    }

    public String readLine() {
        return this.scanner.nextLine();
    }

    public void printException (Exception exception) {
        System.out.println(exception);
    }
}
