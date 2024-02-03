package Duke;

import Duke.Tasks.Task;

import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void sayHello() {
        String greetMessage = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm Corgi!\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n");
        System.out.println(greetMessage);
    }

    public void sayGoodbye() {
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exitMessage);
    }

    public void printAddMessage(int sizeOfTaskList, Task addedTask) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " Got it. I've added this task:\n" +
                        "  [%s][%s] %s\n" +
                        " Now you have %d tasks in the list.\n" +
                        "____________________________________________________________\n",
                addedTask.getTaskType(), addedTask.getStatusIcon(),
                addedTask.toString(), sizeOfTaskList);
        System.out.println(message);
    }

    public void printDeleteMessage(int sizeOfTaskList, Task deletedTask) {
        String deleteMessage = String.format(
                "____________________________________________________________\n" +
                        " Noted. I've removed this task:\n" +
                        "  [%s][%s] %s\n" +
                        " Now you have %d tasks in the list.\n" +
                        "____________________________________________________________\n",
                deletedTask.getTaskType(), deletedTask.getStatusIcon(),
                deletedTask.toString(), sizeOfTaskList);
        System.out.println(deleteMessage);
    }

    public void printList(TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                " Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = String.format("%d.[%s][%s] %s",
                    i + 1, taskList.getTask(i).getTaskType(),
                    taskList.getTask(i).getStatusIcon(), taskList.getTask(i).toString());
            System.out.println(currentTask);
        }
        System.out.println("____________________________________________________________\n");
    }

    public void printMarkDoneMessage(Task taskToBeMarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        "  [%s][%s] %s\n" +
                        "____________________________________________________________\n",
                taskToBeMarked.getTaskType(), taskToBeMarked.getStatusIcon(),
                taskToBeMarked.getDescription());
        System.out.println(message);
    }

    public void printMarkUndoneMessage(Task taskToBeUnmarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " OK, I've marked this task as not done yet:\n" +
                        "  [%s][%s] %s\n" +
                        "____________________________________________________________\n",
                taskToBeUnmarked.getTaskType(), taskToBeUnmarked.getStatusIcon(),
                taskToBeUnmarked.getDescription());
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n" +
                message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
    }

    public void printDateErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n" +
                message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
    }

}

