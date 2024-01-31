package osiris.ui;

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

    public void messageOsirisPrompt() {
        System.out.print(UiOutputs.MESSAGEOSIRISPROMPT);
    }

    public void unsupportedCommandsOutput() {
        System.out.println(UiOutputs.UNSUPPORTEDCOMMANDSOUTPUT);
    }

    public void outputGoodbyes() {
        this.printSeparator();
        System.out.println(UiOutputs.GOODBYES);
        this.printSeparator();
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

    public void markTaskCompletedSuccessNotification(String taskDetails) {
        this.printSeparator();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("        " + taskDetails);
        this.printSeparator();
    }

    public void markTaskIncompleteSuccessNotification(String taskDetails) {
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

    public void printUserTasks(ArrayList<String> taskDetailsList) {
        this.printSeparator();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskDetailsList.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + taskDetailsList.get(i));
        }
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println(UiOutputs.SEPERATOR);
    }

}
