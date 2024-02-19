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
    private String greetings = "Hello sensei, what can Arona do for you?";
    private String goodbye = "Goodbye sensei! Hope to see you soon!";
    private String response;
    public void greetings() {
        response = "Initiating... \n"
                + logo + "\n"
                + greetings + "\n";
    }

    public void quitApplication() {
        response = goodbye + "\n";
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String fullCommand = scanner.nextLine();
        return fullCommand;
    }

    public void printTasks(ArrayList<Task> tasks) {
        response = "Sensei! Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            response += i + 1 + "." + task.toString() + "\n";
            System.out.println(response);
        }
    }

    public void taskAdded(ArrayList<Task> tasks) {
        response = "Arona has added this task to sensei's task list!: \n"
                + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Arona has counted " + tasks.size() + " tasks in the list!";
        System.out.println(response);
    }

    public void taskDeleted(Task task, int size) {
        response = "Arona has removed this task!: \n"
                + task.toString() + "\n"
                + "Arona has counted " + size + " tasks in the list!\n";
    }

    public void isMarked(boolean status) {
        response = "Sensei, the task has already been marked as " + (status ? "done!" : "not done!") + "\n";
    }

    public void markTask() {
        response = "Congratulation, sensei! Arona has marked the task as done!\n";
    }

    public void unmarkTask() {
        response = "I understand, sensei! Arona has marked the task as not done!\n";
    }

    public void checkResponse() {
        response = "Sensei! Arona found this task in your tasklist! Do you want to add it again?";
    }

    public void discardDuplicateTask() {
        response = "Arona will be discarding this duplicate task!";
    }

    public String showLine() {
        return "---------------------------------";
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String res) {
        response = res;
    }
}
