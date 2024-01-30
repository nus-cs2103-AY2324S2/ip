package pingmebot;

import pingmebot.task.Task;

import java.util.Scanner;

public class UI {
    private final Scanner sc;
    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        System.out.println(greetingMsg);
    }

    public void sayGoodbye() {
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println("\n" + exitMsg);
    }

    public void listText() {
        System.out.println("Here are the tasks in your list:");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void additionToTasksText(Task task, TaskList allTasks) {
        String toUserUponAddition = "";
        toUserUponAddition += ("\n" + "Got it. I've added this task:");
        toUserUponAddition += "\n"  + "  " + task.toString();
        toUserUponAddition += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponAddition);
    }

    public void deletionToTasksText(int taskNumber, TaskList allTasks) {
        String toUserUponDeletion = "";
        toUserUponDeletion += "Noted. I've removed this task:";
        toUserUponDeletion += "\n"  + "  " + allTasks.taskToString(taskNumber);
        allTasks.removeTask(taskNumber);
        toUserUponDeletion += "\n" + "Now you have " + allTasks.getTaskSize() + " tasks in the list.";
        System.out.println(toUserUponDeletion);
    }

    public void markTaskText(int taskNum, TaskList allTasks) throws myBotException {
        String toUserUponMarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals("X")) {
            throw new myBotException("You cannot mark task again which has been completed!");
        }
        allTasks.taskMarkAsDone(taskNum);
        toUserUponMarkingTask += "Nice! I've marked this task as done:";
        toUserUponMarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponMarkingTask);
    }

    public void unmarkTaskText(int taskNum, TaskList allTasks) throws myBotException {
        String toUserUponUnmarkingTask = "";
        if (allTasks.taskStatusIcon(taskNum).equals(" ")) {
            throw new myBotException("You cannot un-mark task which has not been marked!");
        }
        allTasks.taskUncheckTask(taskNum);
        toUserUponUnmarkingTask += "OK, I've marked this task as not done yet:";
        toUserUponUnmarkingTask += "\n" + "  " + allTasks.taskToString(taskNum);
        System.out.println(toUserUponUnmarkingTask);
    }


    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }





}
