package UI;

import Task.Task;

import java.util.ArrayList;

public class Ui {

    public void outputIntroductions() {
        this.printSeparator();
        for (String line : UiOutputs.NAMEASCIIArt) {
            System.out.println(line);
        }
        this.printSeparator();
        System.out.println(UiOutputs.INTRODUCTIONS);
        this.printSeparator();
    }

    public void outputGoodbyes() {
        System.out.println(UiOutputs.GOODBYES);
    }

    private void printSeparator() {
        System.out.println(UiOutputs.SEPERATOR);
    }

    public void addToDoTaskSuccessNotification(String taskDetails, int taskCount) {
        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + taskDetails);
        System.out.printf("     Now you have %d tasks in the list.%n", taskCount);
        this.printSeparator();
    }

    public void addDeadlineTaskSuccessNotification(String taskDetails, int taskCount) {
        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + taskDetails);
        System.out.printf("     Now you have %d tasks in the list.%n", taskCount);
        this.printSeparator();
    }

    public void addEventTaskSuccessNotification(String taskDetails, int taskCount) {
        this.printSeparator();
        System.out.println("     Got it. I've added this task:");
        System.out.println("        " + taskDetails);
        System.out.printf("     Now you have %d tasks in the list.%n", taskCount);
        this.printSeparator();
    }

    public void markTaskCompletedSuccessNotification(String taskDetails){
        this.printSeparator();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("        " + taskDetails);
        this.printSeparator();
    }

    public void markTaskIncompleteSuccessNotification(String taskDetails){
        this.printSeparator();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("        " + taskDetails);
        this.printSeparator();
    }

    public void removeTaskSuccessNotification(String taskDetails, int taskCount) {
        this.printSeparator();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("        " + taskDetails);
        System.out.printf("     Now you have %d tasks in the list.%n", taskCount);
        this.printSeparator();
    }

    public void printUserTasks(ArrayList<String> toPrint){
        this.printSeparator();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + toPrint.get(i));
        }
        this.printSeparator();
    }


}
