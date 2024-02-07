package judy.ui;

import judy.type.*;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(Messages.GREET_MESSAGE);
    }

    public void showLine() {
        System.out.println(Messages.LINE);
    }

    public void showAddTask(Task task, int size) {
        System.out.println( Messages.ADD_TASK_MESSAGE +
                task.toString() +
                Messages.printTaskSize(size));
    }
    public void showDeleteTask(Task task, int size) {
        System.out.println(Messages.DELETE_TASK_MESSAGE + task.toString() + Messages.printTaskSize(size));
    }

    public void showMarkTask(Task task) {
        String s = Messages.MARK_TASK_MESSSAGE + task.toString();
        System.out.println(s);
    }

    public void showUnmarkTask(Task task) {
        System.out.println(Messages.UNMARK_TASK_MESSAGE + task.toString());
    }
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println(Messages.EMPTY_TASKLIST_MESSAGE);
        } else {
            System.out.println(Messages.LIST_TASKS_MESSAGE);
            for(int i = 0; i < taskList.getSize(); i++) {
                int seq = i+1;
                Task t = taskList.get(i);
                System.out.println ("  " + seq + ". " + t.toString());
            }
        }
    }
    public void showError(String errorMessage) {
         System.out.println(errorMessage);
    }
    public void showGoodbye() {
        System.out.println(Messages.GOODBYE_MESSAGE);
    }
}
