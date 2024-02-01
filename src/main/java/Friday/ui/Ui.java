package Friday.ui;

import java.util.Scanner;
import Friday.task.TaskList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayTaskList(TaskList tasks) {
        for (int i = 0; i < tasks.length(); i++) {
            System.out.println(i + 1 + ". " + tasks.getTask(i).toString());
        }
    }


}
